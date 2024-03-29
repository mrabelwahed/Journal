package com.droidcourses.bookmarks

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.hilt.navigation.compose.hiltViewModel
import com.droidcourses.designsystem.mediumSpacing
import com.droidcourses.designsystem.smallSpacing
import com.droidcourses.news_bookmarks.common.ArticleCard
import com.droidcourses.news_bookmarks.domain.models.Article


@Composable
fun BookmarkScreen(
    viewModel: BookmarkViewModel = hiltViewModel()
) {
    val articles = viewModel.localNewsState.value.data

    Column (
        Modifier
            .padding(mediumSpacing)
            .statusBarsPadding()
    ) {
        Text(
            text = stringResource(id = R.string.bookmark),
            style = MaterialTheme.typography.headlineLarge.copy(fontWeight = FontWeight.Bold)
        )
        BookmarkedArticles(articles = articles)
    }
}

    @Composable
    fun BookmarkedArticles(
        modifier: Modifier = Modifier,
        articles: List<Article>,
        onClick: ((Article) -> Unit)? = null,
    ) {
        if (articles.isEmpty()){
         // EmptyScreen()
        }
            LazyColumn(
                modifier = modifier
                    .fillMaxSize(),
                verticalArrangement = Arrangement.spacedBy(smallSpacing),
                contentPadding = PaddingValues(all = smallSpacing)
            ) {
                items(articles) { article ->
                    ArticleCard(
                        modifier = modifier,
                        article = article,
                        onClick = { onClick?.invoke(article) }
                    )
                }
            }
    }
