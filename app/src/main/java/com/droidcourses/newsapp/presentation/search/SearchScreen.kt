package com.droidcourses.newsapp.presentation.search

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.paging.compose.collectAsLazyPagingItems
import com.droidcourses.newsapp.designsystem.mediumSpacing
import com.droidcourses.newsapp.domain.models.Article
import com.droidcourses.newsapp.presentation.components.EmptyScreen
import com.droidcourses.newsapp.presentation.components.SearchBar
import com.droidcourses.newsapp.presentation.home.ArticleList

@Composable
fun SearchScreen(
    state: SearchState,
    event: (SearchScreenEvent) -> Unit,
    onArticleClicked: (Article) -> Unit
) {
    Column (modifier = Modifier.fillMaxSize()) {
        SearchBar(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = mediumSpacing, start = mediumSpacing, end = mediumSpacing)
                .statusBarsPadding(),
            text = state.searchQuery ,
            isReadOnly = false,
            onSearch = {
             event.invoke(SearchScreenEvent.SearchEvent)
            },
            onValueChanged = {
                event.invoke(SearchScreenEvent.UpdateSearchKeyword(it))
            },
            onClick = {}
        )
        state.articles?.let {
            val articles = it.collectAsLazyPagingItems()
            ArticleList(articles = articles){ article ->
                onArticleClicked.invoke(article)
            }
        }?: run {
            EmptyScreen(fromSearch = true)
        }
    }

}