package com.droidcourses.newsapp.presentation.home

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.paging.compose.LazyPagingItems
import com.droidcourses.newsapp.designsystem.mediumSpacing
import com.droidcourses.newsapp.designsystem.smallSpacing
import com.droidcourses.newsapp.domain.models.Article
import com.droidcourses.newsapp.presentation.components.SearchBar


@Composable
fun HomeScreen(articles: LazyPagingItems<Article>, navigateToSearch:() -> Unit) {
    var text by remember { mutableStateOf("") }

    Column(modifier = Modifier.fillMaxSize()) {
        SearchBar(
            modifier = Modifier
               .fillMaxWidth()
               .padding(horizontal = mediumSpacing, vertical = smallSpacing),
            text = text,
            isReadOnly = true,
            onSearch = {},
            onValueChanged = {
                text = it
            },
            onClick = {
              navigateToSearch.invoke()
            }
        )
        ArticleList(articles = articles) {}
    }

}