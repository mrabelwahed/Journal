package com.loc.newsapp.presentation.components

import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@Composable
fun SecondaryButton(modifier: Modifier = Modifier, text: String, onclick:() -> Unit) {
    TextButton(onClick = onclick) {
        Text(text = text)
    }
}