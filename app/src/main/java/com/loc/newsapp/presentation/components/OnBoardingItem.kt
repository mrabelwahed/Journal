package com.loc.newsapp.presentation.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import com.loc.newsapp.R
import com.loc.newsapp.designsystem.mediumSpacing
import com.loc.newsapp.designsystem.smallSpacing
import com.loc.newsapp.presentation.onboarding.Page

@Composable
fun OnBoardingItem(modifier: Modifier, page: Page) {
    Column {
        Image(
            painter = painterResource(id = page.image),
            contentDescription = null,
            contentScale = ContentScale.Crop,
            modifier = modifier
                .fillMaxWidth()
                .fillMaxHeight(fraction = 0.6f)
        )
        Spacer(modifier = modifier.height(mediumSpacing))
        Text(
            text = page.title,
            modifier = modifier.padding(horizontal = mediumSpacing),
            style = MaterialTheme.typography.titleLarge.copy(fontWeight = FontWeight.Bold),
            color = MaterialTheme.colorScheme.onBackground
        )
        Spacer(modifier = modifier.height(smallSpacing))
        Text(
            text = page.description,
            modifier = modifier.padding(horizontal = mediumSpacing),
            style = MaterialTheme.typography.bodySmall,
            color = MaterialTheme.colorScheme.onBackground
        )


    }
}