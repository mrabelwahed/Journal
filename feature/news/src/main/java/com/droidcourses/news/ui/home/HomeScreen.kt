package com.droidcourses.news.ui.home

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.paging.compose.collectAsLazyPagingItems
import com.droidcourses.designsystem.mediumSpacing
import com.droidcourses.designsystem.smallSpacing
import com.droidcourses.designsystem.R as designR
import com.droidcourses.uicomponents.SearchBar


@Composable
fun HomeScreen(
    viewModel: HomeViewModel = hiltViewModel(),
    navigateToSearch: (() -> Unit)? = null,
    onArticleClicked:(com.droidcourses.news_bookmarks.domain.models.Article) -> Unit
) {
    val articles = viewModel.news.collectAsLazyPagingItems()
    var text by remember { mutableStateOf("") }

    Column(modifier = Modifier.fillMaxSize()) {
        Text(
            text = stringResource(id = designR.string.app_name),
            fontWeight = FontWeight.Bold,
            fontSize = 30.sp,
            modifier = Modifier
            .padding(horizontal = mediumSpacing, vertical = smallSpacing))
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
              navigateToSearch?.invoke()
            }
        )
        ArticleList(articles = articles) {
           article ->  onArticleClicked.invoke(article)
        }
    }

}