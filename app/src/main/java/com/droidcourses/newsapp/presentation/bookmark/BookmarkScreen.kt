package com.droidcourses.newsapp.presentation.bookmark

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import com.droidcourses.newsapp.R

@Composable
fun BookmarkScreen() {
    Text(
         text = stringResource(id = R.string.bookmark),
         style = MaterialTheme.typography.headlineLarge
        )
}