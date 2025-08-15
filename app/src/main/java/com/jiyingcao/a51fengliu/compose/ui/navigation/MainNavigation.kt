package com.jiyingcao.a51fengliu.compose.ui.navigation

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBars
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.jiyingcao.a51fengliu.compose.ui.navigation.components.CustomBottomNavigation
import com.jiyingcao.a51fengliu.compose.ui.tab.home.HomeScreen
import com.jiyingcao.a51fengliu.compose.ui.tab.merchant.MerchantListScreen
import com.jiyingcao.a51fengliu.compose.ui.tab.profile.ProfileScreen
import com.jiyingcao.a51fengliu.compose.ui.tab.record.RecordTabScreen
import com.jiyingcao.a51fengliu.compose.ui.tab.street.StreetTabScreen

@Composable
fun MainNavigation(
    currentTab: MainTab,
    onTabSelected: (MainTab) -> Unit,
    modifier: Modifier = Modifier
) {
    Scaffold(
        modifier = modifier
            .fillMaxSize()
            .windowInsetsPadding(WindowInsets.statusBars),
        bottomBar = {
            CustomBottomNavigation(
                currentTab = currentTab,
                onTabSelected = onTabSelected
            )
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
        ) {
            when (currentTab) {
                MainTab.HOME -> HomeScreen()
                MainTab.RECORD -> RecordTabScreen()
                MainTab.STREET -> StreetTabScreen()
                MainTab.MERCHANT -> MerchantListScreen()
                MainTab.PROFILE -> ProfileScreen()
            }
        }
    }
}