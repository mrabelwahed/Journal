package com.droidcourses.details

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.droidcourses.news.ui.details.NewsDetailsScreen

const val ARTICLE_DETAILS_SCREEN = "article_details_screen"

fun NavController.navigateToNewsDetails() {
    navigate(ARTICLE_DETAILS_SCREEN)
}

fun NavGraphBuilder.newsDetailsScreen(navController: NavController, onNavigateUp: () -> Unit) {
    composable(ARTICLE_DETAILS_SCREEN) {
        NewsDetailsScreen(navController = navController, onNavigateUp = onNavigateUp)
    }
}