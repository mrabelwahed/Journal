package com.droidcourses.newsapp.presentation.details


import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
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
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import coil.compose.AsyncImage
import com.droidcourses.newsapp.R
import com.droidcourses.newsapp.designsystem.largeSpacing
import com.droidcourses.newsapp.designsystem.mediumSpacing
import com.droidcourses.newsapp.domain.models.Article
import com.droidcourses.newsapp.presentation.components.DetailsTopBar
import com.droidcourses.newsapp.util.AppConst

@Composable
fun NewsDetailsScreen(
    viewModel: DetailsViewModel = hiltViewModel(),
    navController: NavController,
    onNavigateUp: () -> Unit
) {

    val article  = navController.previousBackStackEntry?.savedStateHandle?.get<Article>(AppConst.ARTICLE)

    Column {
        DetailsTopBar(
            modifier = Modifier.fillMaxWidth(),
            onNavigateUp = onNavigateUp,
            onBrowserClicked = { viewModel.onEvent(DetailsScreenEvent.BrowseClicked(article?.url ?: return@DetailsTopBar)) },
            onShareClicked = {  viewModel.onEvent(DetailsScreenEvent.ShareClicked(article?.url ?: return@DetailsTopBar)) },
            onBookmarkClicked = {  viewModel.onEvent(DetailsScreenEvent.BookmarkClicked(article ?: return@DetailsTopBar)) })
        


        LazyColumn(contentPadding = PaddingValues(mediumSpacing)) {
            item {
                AsyncImage(
                    model = article?.urlToImage,
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(220.dp)
                        .clip(MaterialTheme.shapes.medium),
                    contentDescription = null,
                    placeholder = painterResource(id = R.drawable.onboarding3x),
                    error = painterResource(id = R.drawable.onboarding3x),
                    contentScale = ContentScale.Crop
                )

                Text(
                    text = article?.title ?: return@item,
                    style = MaterialTheme.typography.titleMedium,
                    modifier = Modifier.padding(top = mediumSpacing)
                )
                
                Spacer(modifier = Modifier.height(largeSpacing))

                Text(
                    text = article.content ?: return@item,
                    style = MaterialTheme.typography.bodySmall
                )
            }
        }
    }
}