package com.loc.newsapp.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navigation
import com.loc.newsapp.presentation.home.HomeScreen
import com.loc.newsapp.presentation.onboarding.OnBoardingScreen
import com.loc.newsapp.presentation.onboarding.OnBoardingViewModel

@Composable
fun NavGraph(startDestination: String) {
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = startDestination) {

        navigation(
            route = Screen.AppStart.route,
            startDestination = Screen.OnBoardingScreen.route
        ){
            composable(Screen.OnBoardingScreen.route) {
                val viewModel: OnBoardingViewModel = hiltViewModel()
                OnBoardingScreen(onBoardingEvent = viewModel::onEvent)
            }
        }

        navigation(
            route = Screen.NewsNav.route,
            startDestination = Screen.Home.route
        ){
            composable(Screen.Home.route) {
//                val viewModel: OnBoardingViewModel = hiltViewModel()
//                OnBoardingScreen(onBoardingEvent = viewModel::onEvent)
//                HomeScreen()
            }
        }
    }
}