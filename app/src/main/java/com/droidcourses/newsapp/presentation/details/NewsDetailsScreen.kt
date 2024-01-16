package com.droidcourses.newsapp.presentation.details


import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.droidcourses.newsapp.R
import com.droidcourses.newsapp.designsystem.mediumSpacing
import com.droidcourses.newsapp.domain.models.Article
import com.droidcourses.newsapp.presentation.components.DetailsTopBar

@Composable
fun NewsDetailsScreen(
    article: Article,
    onEvent: (DetailsScreenEvent) -> Unit
) {

    Column {
        DetailsTopBar(
            modifier = Modifier.fillMaxWidth(),
            onNavigateUp = { onEvent.invoke(DetailsScreenEvent.NavigateUp) },
            onBrowserClicked = { onEvent.invoke(DetailsScreenEvent.BrowseClicked(article.url)) },
            onShareClicked = { onEvent.invoke(DetailsScreenEvent.ShareClicked(article.url)) },
            onBookmarkClicked = { onEvent.invoke(DetailsScreenEvent.BookmarkClicked) }) 
        


        LazyColumn(contentPadding = PaddingValues(mediumSpacing)) {
            item {
                AsyncImage(
                    model = article.urlToImage,
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(220.dp)
                        .clip(MaterialTheme.shapes.medium),
                    contentDescription = null,
                    placeholder = painterResource(id = R.drawable.onboarding3x),
                    contentScale = ContentScale.Crop
                )

                Text(
                    text = article.title,
                    style = MaterialTheme.typography.titleMedium,
                    modifier = Modifier.padding(top = mediumSpacing)
                )

                Text(
                    text = article.content,
                    style = MaterialTheme.typography.bodySmall
                )
            }


        }
    }


}

//@Preview(showBackground = true)
//@Preview(showBackground = true, uiMode = UI_MODE_NIGHT_YES)
//@Composable
//fun preview() {
//    val viewModel: DetailsViewModel = hiltViewModel()
//    NewsDetailsScreen(article = , onEvent = viewModel::onEvent)
//}