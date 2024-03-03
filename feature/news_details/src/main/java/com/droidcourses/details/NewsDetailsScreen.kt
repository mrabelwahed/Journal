package com.droidcourses.news.ui.details


import android.content.Context
import android.content.Intent
import android.net.Uri
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
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import coil.compose.AsyncImage
import com.droidcourses.common.util.AppConst
import com.droidcourses.designsystem.largeSpacing
import com.droidcourses.designsystem.mediumSpacing
import com.droidcourses.details.DetailsScreenEvent
import com.droidcourses.details.DetailsViewModel
import com.droidcourses.news_bookmarks.domain.models.Article
import com.droidcourses.uicomponents.DetailsTopBar
import com.droidcourses.designsystem.R as designR

@Composable
fun NewsDetailsScreen(
    viewModel: DetailsViewModel = hiltViewModel(),
    navController: NavController,
    onNavigateUp: () -> Unit
) {
    val article = navController.previousBackStackEntry?.savedStateHandle?.get<Article>(AppConst.ARTICLE)
    val context = LocalContext.current
    Column {
        DetailsTopBar(
            modifier = Modifier.fillMaxWidth(),
            onNavigateUp = onNavigateUp,
            onBrowserClicked = {
                context.onBrowserClicked(article?.url ?: return@DetailsTopBar)
            },
            onShareClicked = {
                context.onShareClicked(article?.url ?: return@DetailsTopBar)
            },
            onBookmarkClicked = {
                viewModel.onEvent(
                    DetailsScreenEvent.BookmarkClicked(
                        article ?: return@DetailsTopBar
                    )
                )
            })



        LazyColumn(contentPadding = PaddingValues(mediumSpacing)) {
            item {
                AsyncImage(
                    model = article?.urlToImage,
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(220.dp)
                        .clip(MaterialTheme.shapes.medium),
                    contentDescription = null,
                    placeholder = painterResource(id = designR.drawable.onboarding3x),
                    error = painterResource(id = designR.drawable.onboarding3x),
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

private fun Context.onShareClicked(url: String) {
    Intent().also {
        it.action = Intent.ACTION_SEND
        it.putExtra(Intent.EXTRA_TEXT, url)
        it.type = "text/plain"
        if (it.resolveActivity(this.packageManager) != null)
            this.startActivity(it)
    }
}

private fun Context.onBrowserClicked(url: String) {
    Intent().also {
        it.action = Intent.ACTION_VIEW
        it.data = Uri.parse(url)
        if (it.resolveActivity(this.packageManager) != null)
            this.startActivity(it)
    }
}

