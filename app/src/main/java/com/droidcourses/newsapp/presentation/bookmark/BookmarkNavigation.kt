package com.droidcourses.newsapp.presentation.bookmark

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable

const val BOOKMARK_SCREEN = "bookmark_screen"
fun NavController.navigateToBookMark() {
    navigate(BOOKMARK_SCREEN)
}

fun NavGraphBuilder.bookmarkScreen() {
    composable(BOOKMARK_SCREEN) {
        BookmarkScreen()
    }
}