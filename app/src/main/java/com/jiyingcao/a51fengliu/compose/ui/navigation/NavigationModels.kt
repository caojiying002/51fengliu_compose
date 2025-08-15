package com.jiyingcao.a51fengliu.compose.ui.navigation

import androidx.annotation.DrawableRes
import androidx.compose.runtime.Stable
import com.jiyingcao.a51fengliu.compose.R

/**
 * 主要Tab枚举
 *
 * @property title Tab显示文本
 * @property iconRes Tab图标资源ID
 * @property route Tab路由标识（用于状态保存等）
 */
@Stable
enum class MainTab(
    val title: String,
    @DrawableRes val iconRes: Int,
    val route: String
) {
    HOME(
        title = "首页",
        iconRes = R.drawable.ic_home,
        route = "home"
    ),
    RECORD(
        title = "信息",
        iconRes = R.drawable.ic_record,
        route = "record"
    ),
    STREET(
        title = "暗巷",
        iconRes = R.drawable.ic_street,
        route = "street"
    ),
    MERCHANT(
        title = "商家",
        iconRes = R.drawable.ic_merchant,
        route = "merchant"
    ),
    PROFILE(
        title = "我的",
        iconRes = R.drawable.ic_profile,
        route = "profile"
    );

    companion object {
        /**
         * 根据路由字符串获取对应的Tab
         */
        fun fromRoute(route: String): MainTab {
            return entries.find { it.route == route } ?: HOME
        }

        /**
         * 获取所有Tab列表
         */
        fun getAllTabs(): List<MainTab> = entries
    }
}

/**
 * 主导航UI状态
 *
 * @property currentTab 当前选中的Tab
 * @property isLoading 是否在加载中
 */
@Stable
data class MainNavigationUiState(
    val currentTab: MainTab = MainTab.HOME,
    val isLoading: Boolean = false
)