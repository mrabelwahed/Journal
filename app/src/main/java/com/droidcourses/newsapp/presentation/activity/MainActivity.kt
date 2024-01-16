package com.droidcourses.newsapp.presentation.activity

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.background
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.navigation.compose.rememberNavController
import com.droidcourses.newsapp.designsystem.NewsAppTheme
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    private val viewModel: MainViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        installSplashScreen().apply {
            setKeepOnScreenCondition(condition = {
                viewModel.shouldKeepSplash.value
            })
        }

        setContent {
            val isSystemInDark = isSystemInDarkTheme()
            val systemUIColor = rememberSystemUiController()

            SideEffect {
                systemUIColor.setSystemBarsColor(
                    color = Color.Transparent,
                    darkIcons = !isSystemInDark
                )
            }

            NewsAppTheme {
                Box(
                    modifier = Modifier
                        .background(MaterialTheme.colorScheme.background)
                        .fillMaxSize()
                ) {
                    MainScreen(
                        startDestination = viewModel.startDestination.value,
                        navController = rememberNavController()
                    )
                }
            }
        }
    }
}
