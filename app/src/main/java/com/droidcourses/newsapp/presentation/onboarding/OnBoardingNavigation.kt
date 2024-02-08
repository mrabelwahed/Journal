package com.droidcourses.newsapp.presentation.onboarding

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable


const val  ONBOARDING_ROUTE = "onboarding_screen"

fun NavController.navigateToOnBoarding() {
    navigate(ONBOARDING_ROUTE)
}

fun NavGraphBuilder.onBoardingScreen() {
    composable(ONBOARDING_ROUTE) {
        OnBoardingScreen()
    }
}