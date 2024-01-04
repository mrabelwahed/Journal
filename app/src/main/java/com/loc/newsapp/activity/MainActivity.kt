package com.loc.newsapp.activity

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import com.loc.newsapp.presentation.onboarding.OnBoardingScreen
import com.loc.newsapp.designsystem.NewsAppTheme
import com.loc.newsapp.presentation.navigation.NavGraph
import com.loc.newsapp.presentation.navigation.Screen
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
            NewsAppTheme {
              NavGraph(viewModel.startDestination.value)
            }
        }
    }
}
