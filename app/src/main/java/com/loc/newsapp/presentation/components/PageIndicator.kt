package com.loc.newsapp.presentation.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import com.loc.newsapp.designsystem.BlueGray
import com.loc.newsapp.designsystem.indicatorSize

@Composable
fun PageIndicator(
    modifier:Modifier = Modifier,
    pages: Int,
    selectedPage: Int,
    selectedColor: Color = MaterialTheme.colorScheme.primary,
    unselectedColor: Color = BlueGray
) {
    Row (
        modifier = modifier,
        horizontalArrangement = Arrangement.SpaceBetween,
    ){
        repeat(pages){ page ->
            Box(modifier = Modifier
                .size(indicatorSize)
                .clip(CircleShape)
                .background(if(page == selectedPage) selectedColor else unselectedColor))
        }
    }

}