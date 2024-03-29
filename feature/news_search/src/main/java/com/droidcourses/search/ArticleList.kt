package com.droidcourses.search

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.semantics
import androidx.paging.LoadState
import androidx.paging.compose.LazyPagingItems
import com.droidcourses.designsystem.mediumSpacing
import com.droidcourses.designsystem.smallSpacing
import com.droidcourses.news_bookmarks.common.ArticleCard
import com.droidcourses.uicomponents.ArticleCardShimmerEffect

@Composable
fun ArticleList(
    modifier: Modifier = Modifier,
    articles: LazyPagingItems<com.droidcourses.news_bookmarks.domain.models.Article>,
    onClick: ((com.droidcourses.news_bookmarks.domain.models.Article) -> Unit)? = null)
{

    val handlePagingResult = handlePagingResult(articles)
    if (handlePagingResult) {
        LazyColumn(
            modifier = modifier
                .fillMaxSize()
                .padding(mediumSpacing)
                .semantics { contentDescription = "Article List" },
            verticalArrangement = Arrangement.spacedBy(mediumSpacing),
            contentPadding = PaddingValues(all = smallSpacing)
        ) {
            items(
                count = articles.itemCount,
                key =  {
                    articles[it]?.url ?: ""
                }
            ) {
                articles[it]?.let { article ->
                    ArticleCard(
                        modifier = modifier.semantics { contentDescription = "Article ${article.url}" },
                        article = article,
                        onClick = { onClick?.invoke(article) }
                    )
                }
            }
        }
    }
}

@Composable
fun handlePagingResult(articles: LazyPagingItems<com.droidcourses.news_bookmarks.domain.models.Article>): Boolean {
    val loadState = articles.loadState
    val error = when {
        loadState.refresh is LoadState.Error -> loadState.refresh as LoadState.Error
        loadState.prepend is LoadState.Error -> loadState.prepend as LoadState.Error
        loadState.append is LoadState.Error -> loadState.append as LoadState.Error
        else -> null
    }

    return when {
        loadState.refresh is LoadState.Loading -> {
            ShimmerEffect()
            false
        }

        error != null -> {
           EmptyScreen(error = error)
            false
        }

        else -> {
            true
        }
    }
}

@Composable
fun ShimmerEffect() {
    Column(verticalArrangement = Arrangement.spacedBy(mediumSpacing)) {
        repeat(10) {
            ArticleCardShimmerEffect(
                modifier = Modifier.padding(horizontal = mediumSpacing)
                    .semantics { contentDescription = "Item $it" }
            )
        }
    }
}

