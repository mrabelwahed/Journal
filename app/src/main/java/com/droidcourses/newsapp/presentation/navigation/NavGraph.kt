package com.droidcourses.newsapp.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import com.droidcourses.newsapp.presentation.bookmark.bookmarkScreen
import com.droidcourses.newsapp.presentation.details.navigateToNewsDetails
import com.droidcourses.newsapp.presentation.details.newsDetailsScreen
import com.droidcourses.newsapp.presentation.home.homeScreen
import com.droidcourses.newsapp.presentation.onboarding.onBoardingScreen
import com.droidcourses.newsapp.presentation.search.navigateToSearch
import com.droidcourses.newsapp.presentation.search.searchScreen
import com.droidcourses.newsapp.util.AppConst.ARTICLE

@Composable
fun NavGraph(startDestination: String, navController: NavHostController) {

    NavHost(navController = navController, startDestination = startDestination) {

        onBoardingScreen()

        homeScreen(
            navigateToSearch = {
                navController.navigateToSearch()
            },
            onArticleClicked = {
                navController.currentBackStackEntry?.savedStateHandle?.set(ARTICLE, it)
                navController.navigateToNewsDetails()
            }
        )

        searchScreen(
            onArticleClicked = {
                navController.currentBackStackEntry?.savedStateHandle?.set(ARTICLE, it)
                navController.navigateToNewsDetails()
            }
        )

        bookmarkScreen()

        newsDetailsScreen(
            navController = navController,
            onNavigateUp = { navController.navigateUp() }
        )
    }
}
