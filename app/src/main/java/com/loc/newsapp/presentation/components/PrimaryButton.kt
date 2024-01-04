package com.loc.newsapp.presentation.components

import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import com.loc.newsapp.designsystem.smallSpacing

@Composable
fun PrimaryButton(modifier: Modifier = Modifier, text: String, onCLick:() ->  Unit) {
    Button(
        onClick = onCLick,
        colors = ButtonDefaults.buttonColors(
            contentColor = Color.White,
            containerColor = MaterialTheme.colorScheme.primary
        ),
        shape = RoundedCornerShape(smallSpacing)
    ) {
        Text(text = text, style = MaterialTheme.typography.bodyMedium)
    }
}