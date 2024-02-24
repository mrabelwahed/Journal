package com.droidcourses.newsapp.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import com.droidcourses.bookmarks.bookmarkScreen
import com.droidcourses.newsapp.presentation.details.navigateToNewsDetails
import com.droidcourses.newsapp.presentation.details.newsDetailsScreen
import com.droidcourses.news.ui.home.homeScreen
import com.droidcourses.onboarding.ui.nav.onBoardingScreen
import com.droidcourses.news.ui.search.navigateToSearch
import com.droidcourses.news.ui.search.searchScreen
import com.droidcourses.common.util.AppConst.ARTICLE

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
