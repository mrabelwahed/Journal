package com.droidcourses.newsapp.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import androidx.paging.compose.collectAsLazyPagingItems
import com.droidcourses.newsapp.domain.models.Article
import com.droidcourses.newsapp.presentation.bookmark.BookmarkScreen
import com.droidcourses.newsapp.presentation.bookmark.BookmarkViewModel
import com.droidcourses.newsapp.presentation.details.DetailsViewModel
import com.droidcourses.newsapp.presentation.details.NewsDetailsScreen
import com.droidcourses.newsapp.presentation.home.HomeScreen
import com.droidcourses.newsapp.presentation.home.HomeViewModel
import com.droidcourses.newsapp.presentation.onboarding.OnBoardingScreen
import com.droidcourses.newsapp.presentation.onboarding.OnBoardingViewModel
import com.droidcourses.newsapp.presentation.search.SearchScreen
import com.droidcourses.newsapp.presentation.search.SearchViewModel
import com.droidcourses.newsapp.util.AppConst.ARTICLE

@Composable
fun NavGraph(startDestination: String, navController: NavHostController) {

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
                val viewModel: HomeViewModel = hiltViewModel()
                val articles = viewModel.news.collectAsLazyPagingItems()
                HomeScreen(articles, navigateToSearch = {navController.navigate(Screen.Search.route)}) {
                    navController.currentBackStackEntry?.savedStateHandle?.set(ARTICLE,it)
                    navController.navigate(Screen.Details.route)
                }
            }

            composable(Screen.Search.route) {
                val viewModel: SearchViewModel = hiltViewModel()
                SearchScreen(viewModel.state.value,viewModel::onEvent){
                    navController.currentBackStackEntry?.savedStateHandle?.set(ARTICLE,it)
                    navController.navigate(Screen.Details.route)
                }
            }

            composable(Screen.Details.route) {
                val result = navController.previousBackStackEntry?.savedStateHandle?.get<Article>(ARTICLE)
                val viewModel: DetailsViewModel = hiltViewModel()
                if (result != null) {
                    NewsDetailsScreen( result, onEvent = viewModel::onEvent) {
                        navController.navigateUp()
                    }
                }
            }

            composable(Screen.Bookmark.route) {
                val viewModel: BookmarkViewModel = hiltViewModel()
                BookmarkScreen(viewModel.localNewsState.value.data, viewModel::onEvent)
            }
        }
    }
}