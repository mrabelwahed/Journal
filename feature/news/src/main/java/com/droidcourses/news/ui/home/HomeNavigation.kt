package com.droidcourses.news.ui.home

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.droidcourses.news_bookmarks.domain.models.Article

const val HOME_ROUTE = "home_screen"
fun NavController.navigateToHome() {
    navigate(HOME_ROUTE)
}

fun NavGraphBuilder.homeScreen(navigateToSearch: (() -> Unit)? = null, onArticleClicked:(Article) -> Unit) {
    composable(HOME_ROUTE) {
        HomeScreen(navigateToSearch = navigateToSearch, onArticleClicked =  onArticleClicked)
    }
}