package com.droidcourses.newsapp.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import androidx.navigation.navigation
import androidx.paging.compose.collectAsLazyPagingItems
import com.droidcourses.newsapp.domain.models.Article
import com.droidcourses.newsapp.presentation.bookmark.BookmarkScreen
import com.droidcourses.newsapp.presentation.bookmark.BookmarkViewModel
import com.droidcourses.newsapp.presentation.bookmark.bookmarkScreen
import com.droidcourses.newsapp.presentation.details.ARTICLE_DETAILS_SCREEN
import com.droidcourses.newsapp.presentation.details.DetailsViewModel
import com.droidcourses.newsapp.presentation.details.NewsDetailsScreen
import com.droidcourses.newsapp.presentation.details.navigateToNewsDetails
import com.droidcourses.newsapp.presentation.details.newsDetailsScreen
import com.droidcourses.newsapp.presentation.home.HomeScreen
import com.droidcourses.newsapp.presentation.home.HomeViewModel
import com.droidcourses.newsapp.presentation.home.homeScreen
import com.droidcourses.newsapp.presentation.onboarding.OnBoardingScreen
import com.droidcourses.newsapp.presentation.onboarding.OnBoardingViewModel
import com.droidcourses.newsapp.presentation.onboarding.onBoardingScreen
import com.droidcourses.newsapp.presentation.search.SearchScreen
import com.droidcourses.newsapp.presentation.search.SearchViewModel
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
