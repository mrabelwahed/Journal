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
import com.droidcourses.newsapp.presentation.home.homeScreen
import com.droidcourses.newsapp.presentation.onboarding.OnBoardingScreen
import com.droidcourses.newsapp.presentation.onboarding.OnBoardingViewModel
import com.droidcourses.newsapp.presentation.onboarding.onBoardingScreen
import com.droidcourses.newsapp.presentation.search.SearchScreen
import com.droidcourses.newsapp.presentation.search.SearchViewModel
import com.droidcourses.newsapp.util.AppConst.ARTICLE

@Composable
fun NavGraph(startDestination: String, navController: NavHostController) {

    NavHost(navController = navController, startDestination = startDestination) {


        onBoardingScreen()

        homeScreen(navigateToSearch = {
            navController.navigate(Screen.Search.route)
        }, onArticleClicked = {
            navController.currentBackStackEntry?.savedStateHandle?.set(ARTICLE, it)
            navController.navigate(Screen.Details.route)
        })


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
