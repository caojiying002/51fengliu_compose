package com.jiyingcao.a51fengliu.compose.ui

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.jiyingcao.a51fengliu.compose.ui.navigation.MainNavigation
import com.jiyingcao.a51fengliu.compose.ui.navigation.MainNavigationViewModel
import com.jiyingcao.a51fengliu.compose.ui.theme.AppTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    private val navigationViewModel: MainNavigationViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        // 处理重新登录逻辑
        val isRelogin = intent.getBooleanExtra("IS_RELOGIN", false)
        if (isRelogin) {
            navigationViewModel.navigateToProfile()
        }

        Log.d("MainActivity", "onCreate() called, this = $this")

        setContent {
            AppTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    MainScreen(
                        navigationViewModel = navigationViewModel
                    )
                }
            }
        }
    }

    override fun onNewIntent(intent: Intent) {
        super.onNewIntent(intent)
        Log.d("MainActivity", "onNewIntent() called")

        // 处理新的Intent，比如重新登录
        val isRelogin = intent.getBooleanExtra("IS_RELOGIN", false)
        if (isRelogin) {
            navigationViewModel.navigateToProfile()
        }
    }
}

@Composable
private fun MainScreen(
    navigationViewModel: MainNavigationViewModel
) {
    val uiState by navigationViewModel.uiState.collectAsStateWithLifecycle()

    MainNavigation(
        currentTab = uiState.currentTab,
        onTabSelected = navigationViewModel::selectTab
    )
}