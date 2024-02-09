package com.droidcourses.newsapp.presentation.search

import android.widget.SearchView
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.paging.compose.collectAsLazyPagingItems
import com.droidcourses.newsapp.designsystem.mediumSpacing
import com.droidcourses.newsapp.domain.models.Article
import com.droidcourses.newsapp.presentation.components.EmptyScreen
import com.droidcourses.newsapp.presentation.components.SearchBar
import com.droidcourses.newsapp.presentation.home.ArticleList

@Composable
fun SearchScreen(
    viewModel: SearchViewModel = hiltViewModel(),
    onArticleClicked: (Article) -> Unit
) {

    val state  = viewModel.state.value

    Column (modifier = Modifier.fillMaxSize()) {
        SearchBar(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = mediumSpacing, start = mediumSpacing, end = mediumSpacing)
                .statusBarsPadding(),
            text = state.searchQuery ,
            isReadOnly = false,
            onSearch = {
                viewModel.onEvent(SearchScreenEvent.SearchEvent)
            },
            onValueChanged = {
                viewModel.onEvent(SearchScreenEvent.UpdateSearchKeyword(it))
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