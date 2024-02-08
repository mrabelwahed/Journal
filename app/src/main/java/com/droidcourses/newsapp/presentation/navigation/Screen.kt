package com.droidcourses.newsapp.presentation.navigation

sealed class Screen (val route: String) {
   // object OnBoardingScreen: Screen("onboarding_screen")
//    object Home: Screen("home")
    object Search: Screen("search")
    object Details: Screen("news_detail")
    object Bookmark: Screen("bookmark")
}