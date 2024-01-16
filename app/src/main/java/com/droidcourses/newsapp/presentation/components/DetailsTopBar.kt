package com.droidcourses.newsapp.presentation.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Share
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import com.droidcourses.newsapp.R

@Composable
fun DetailsTopBar(
    modifier: Modifier = Modifier,
    onNavigateUp: () -> Unit,
    onBrowserClicked: () -> Unit,
    onShareClicked: () -> Unit,
    onBookmarkClicked: () -> Unit
) {
    Row(
        modifier = modifier,
        horizontalArrangement = Arrangement.SpaceBetween) {
        IconButton(onClick = onNavigateUp) {
            Icon(
                painter = painterResource(id = R.drawable.ic_back_arrow),
                contentDescription = null
            )
        }
        Row {
            IconButton(onClick = onShareClicked) {
                Icon(imageVector = Icons.Default.Share, contentDescription = null)
            }
            IconButton(onClick = onBookmarkClicked) {
                Icon(
                    painter = painterResource(id = R.drawable.ic_bookmark),
                    contentDescription = null
                )
            }
            IconButton(onClick = onBrowserClicked) {
                Icon(
                    painter = painterResource(id = R.drawable.ic_network),
                    contentDescription = null
                )
            }
        }

    }
}