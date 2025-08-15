package com.jiyingcao.a51fengliu.compose.ui.tab.home

import androidx.compose.runtime.Stable
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

/**
 * 首页UI状态
 */
@Stable
data class HomeUiState(
    val isLoading: Boolean = false,
    val error: String? = null,
    val data: List<String> = emptyList()
)

/**
 * 首页用户操作
 */
sealed interface HomeAction {
    object Refresh : HomeAction
    object LoadMore : HomeAction
    data class ItemClick(val item: String) : HomeAction
}

/**
 * 首页ViewModel
 *
 * 管理首页的业务逻辑和状态，替代原来Fragment中的逻辑
 */
@HiltViewModel
class HomeViewModel @Inject constructor(
    // 注入你的Repository、UseCase等依赖
) : ViewModel() {

    private val _uiState = MutableStateFlow(HomeUiState())
    val uiState: StateFlow<HomeUiState> = _uiState.asStateFlow()

    init {
        loadInitialData()
    }

    /**
     * 处理用户操作
     */
    fun handleAction(action: HomeAction) {
        when (action) {
            is HomeAction.Refresh -> refresh()
            is HomeAction.LoadMore -> loadMore()
            is HomeAction.ItemClick -> handleItemClick(action.item)
        }
    }

    /**
     * 刷新数据
     */
    private fun refresh() {
        viewModelScope.launch {
            _uiState.value = _uiState.value.copy(isLoading = true, error = null)

            try {
                // 这里执行实际的数据加载逻辑
                // val data = repository.getData()

                _uiState.value = _uiState.value.copy(
                    isLoading = false,
                    data = listOf("数据1", "数据2", "数据3") // 模拟数据
                )
            } catch (e: Exception) {
                _uiState.value = _uiState.value.copy(
                    isLoading = false,
                    error = e.message
                )
            }
        }
    }

    /**
     * 加载更多数据
     */
    private fun loadMore() {
        viewModelScope.launch {
            // 实现加载更多逻辑
        }
    }

    /**
     * 处理项目点击
     */
    private fun handleItemClick(item: String) {
        // 处理点击事件，比如导航到详情页
    }

    /**
     * 加载初始数据
     */
    private fun loadInitialData() {
        refresh()
    }
}