package com.jiyingcao.a51fengliu.compose.ui.navigation.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBars
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.jiyingcao.a51fengliu.compose.R
import com.jiyingcao.a51fengliu.compose.ui.navigation.MainTab
import com.jiyingcao.a51fengliu.compose.ui.theme.AppTheme

/**
 * 自定义底部导航组件
 *
 * 完全自定义的底部导航，不依赖Material Design的NavigationBar，
 * 可以灵活定制样式以适应国内APP的设计需求
 */
@Composable
fun CustomBottomNavigation(
    currentTab: MainTab,
    onTabSelected: (MainTab) -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
    ) {
        // 阴影分割线
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(3.dp)
                .background(
                    brush = Brush.verticalGradient(
                        colors = listOf(
                            Color.Black.copy(alpha = 0.1f),
                            Color.Transparent
                        )
                    )
                )
        )

        // 底部导航内容
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .background(colorResource(id = R.color.surface))
                .windowInsetsPadding(WindowInsets.navigationBars)
                .padding(vertical = 8.dp),
            horizontalArrangement = Arrangement.SpaceEvenly,
            verticalAlignment = Alignment.CenterVertically
        ) {
            MainTab.getAllTabs().forEach { tab ->
                TabItem(
                    tab = tab,
                    isSelected = currentTab == tab,
                    onClick = { onTabSelected(tab) },
                    modifier = Modifier.weight(1f)
                )
            }
        }
    }
}

/**
 * 单个Tab项组件
 */
@Composable
private fun TabItem(
    tab: MainTab,
    isSelected: Boolean,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    val interactionSource = remember { MutableInteractionSource() }

    // 根据选中状态决定颜色
    val contentColor = if (isSelected) {
        colorResource(id = R.color.tab_selected)  // 粉色选中状态
    } else {
        colorResource(id = R.color.tab_unselected)  // 灰色未选中状态
    }

    Column(
        modifier = modifier
            .clip(RoundedCornerShape(8.dp))
            .clickable(
                interactionSource = interactionSource,
                indication = null // 移除水波纹效果，使用自定义点击效果
            ) { onClick() }
            .padding(horizontal = 4.dp, vertical = 8.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        // Tab图标
        Icon(
            painter = painterResource(id = tab.iconRes),
            contentDescription = tab.title,
            tint = contentColor,
            modifier = Modifier.size(24.dp)
        )

        Spacer(modifier = Modifier.height(4.dp))

        // Tab文字
        Text(
            text = tab.title,
            color = contentColor,
            fontSize = 10.sp,
            fontWeight = if (isSelected) FontWeight.Medium else FontWeight.Normal,
            textAlign = TextAlign.Center,
            maxLines = 1
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun CustomBottomNavigationPreview() {
    AppTheme {
        CustomBottomNavigation(
            currentTab = MainTab.HOME,
            onTabSelected = {}
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun TabItemPreview() {
    AppTheme {
        Row {
            TabItem(
                tab = MainTab.HOME,
                isSelected = true,
                onClick = {}
            )
            TabItem(
                tab = MainTab.RECORD,
                isSelected = false,
                onClick = {}
            )
        }
    }
}