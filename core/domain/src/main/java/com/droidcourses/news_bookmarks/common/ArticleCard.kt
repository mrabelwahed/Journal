package com.droidcourses.news_bookmarks.common


import androidx.compose.foundation.clickable
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.droidcourses.designsystem.smallSpacing
import com.droidcourses.news_bookmarks.domain.models.Article
import com.droidcourses.designsystem.R as designR


@Composable
fun ArticleCard(modifier: Modifier, article: Article, onClick: (() -> Unit)? = null) {
    Row (modifier.clickable { onClick?.invoke() }) {
        AsyncImage(
            model = article.urlToImage,
            contentDescription = null,
            contentScale = ContentScale.Crop,
            modifier = modifier
                .size(80.dp)
                .clip(MaterialTheme.shapes.medium),
            placeholder = painterResource(id = if (isSystemInDarkTheme()) designR.drawable.placeholder_dark else designR.drawable.placeholder),
            error = painterResource(id = if (isSystemInDarkTheme()) designR.drawable.error_image_dark else designR.drawable.error_image),
        )
        Column(
            modifier = modifier.padding(smallSpacing)
        ) {
            Text(
                text = article.title ?: return,
                style = MaterialTheme.typography.bodyMedium.copy(fontWeight = FontWeight.SemiBold),
                maxLines = 2,
                color = colorResource(id = designR.color.text_title),
                overflow = TextOverflow.Ellipsis
            )
            Row(verticalAlignment = Alignment.CenterVertically) {
                Text(
                    text = article.source?.name ?: return,
                    style = MaterialTheme.typography.labelSmall,
                    color = colorResource(id = designR.color.body)
                )
                Spacer(modifier = modifier.width(smallSpacing))
                Icon(
                    painter = painterResource(id = designR.drawable.ic_time),
                    contentDescription = null,
                    tint = colorResource(id = designR.color.body)
                )
                Spacer(modifier = modifier.width(smallSpacing))
                Text(
                    text = article.publishedAt ?: return,
                    style = MaterialTheme.typography.labelSmall,
                    color = colorResource(id = designR.color.body)
                )
            }

        }
    }
}