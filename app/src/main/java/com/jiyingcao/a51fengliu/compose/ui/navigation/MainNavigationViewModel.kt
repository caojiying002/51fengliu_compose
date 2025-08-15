package com.jiyingcao.a51fengliu.compose.ui.navigation

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

/**
 * 主导航ViewModel
 *
 * 负责管理底部Tab导航的状态，包括：
 * - 当前选中的Tab
 * - Tab切换逻辑
 * - 状态保存和恢复
 */
@HiltViewModel
class MainNavigationViewModel @Inject constructor(
    private val savedStateHandle: SavedStateHandle
) : ViewModel() {

    private val _uiState = MutableStateFlow(
        MainNavigationUiState(
            currentTab = getSavedTab()
        )
    )
    val uiState: StateFlow<MainNavigationUiState> = _uiState.asStateFlow()

    /**
     * 选择Tab
     *
     * @param tab 要选择的Tab
     */
    fun selectTab(tab: MainTab) {
        viewModelScope.launch {
            if (_uiState.value.currentTab != tab) {
                _uiState.value = _uiState.value.copy(currentTab = tab)
                saveCurrentTab(tab)
            }
        }
    }

    /**
     * 导航到首页
     */
    fun navigateToHome() {
        selectTab(MainTab.HOME)
    }

    /**
     * 导航到个人中心
     */
    fun navigateToProfile() {
        selectTab(MainTab.PROFILE)
    }

    /**
     * 导航到信息页面
     */
    fun navigateToRecord() {
        selectTab(MainTab.RECORD)
    }

    /**
     * 导航到暗巷页面
     */
    fun navigateToStreet() {
        selectTab(MainTab.STREET)
    }

    /**
     * 导航到商家页面
     */
    fun navigateToMerchant() {
        selectTab(MainTab.MERCHANT)
    }

    /**
     * 获取当前选中的Tab
     */
    fun getCurrentTab(): MainTab {
        return _uiState.value.currentTab
    }

    /**
     * 保存当前Tab到SavedStateHandle
     */
    private fun saveCurrentTab(tab: MainTab) {
        savedStateHandle[KEY_CURRENT_TAB] = tab.route
    }

    /**
     * 从SavedStateHandle恢复保存的Tab
     */
    private fun getSavedTab(): MainTab {
        val savedRoute = savedStateHandle.get<String>(KEY_CURRENT_TAB)
        return if (savedRoute != null) {
            MainTab.fromRoute(savedRoute)
        } else {
            MainTab.HOME
        }
    }

    companion object {
        private const val KEY_CURRENT_TAB = "current_tab"
    }
}