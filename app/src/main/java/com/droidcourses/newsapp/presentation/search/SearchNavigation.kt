package com.droidcourses.newsapp.presentation.search

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.droidcourses.newsapp.domain.models.Article

const val SEARCH_SCREEN = "search_screen"

fun NavController.navigateToSearch() {
    navigate(SEARCH_SCREEN)
}

fun NavGraphBuilder.searchScreen(onArticleClicked: (Article) -> Unit) {
    composable(SEARCH_SCREEN) {
        SearchScreen(
            onArticleClicked = onArticleClicked
        )
    }
}