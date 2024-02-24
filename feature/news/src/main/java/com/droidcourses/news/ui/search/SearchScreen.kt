package com.droidcourses.news.ui.search

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.collectAsLazyPagingItems
import com.droidcourses.designsystem.mediumSpacing
import com.droidcourses.news.ui.EmptyScreen
import com.droidcourses.news.ui.home.ArticleList
import com.droidcourses.uicomponents.SearchBar

@Composable
fun SearchScreen(
    viewModel: SearchViewModel = hiltViewModel(),
    onArticleClicked: (com.droidcourses.news_bookmarks.domain.models.Article) -> Unit
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
            val articles: LazyPagingItems<com.droidcourses.news_bookmarks.domain.models.Article> = it.collectAsLazyPagingItems()
            ArticleList(articles = articles){ article ->
                onArticleClicked.invoke(article)
            }
        }?: run {
            EmptyScreen(fromSearch = true)
        }
    }

}