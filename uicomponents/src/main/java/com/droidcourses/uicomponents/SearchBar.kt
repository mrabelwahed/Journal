package com.droidcourses.uicomponents

import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsPressedAsState
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.composed
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.unit.dp
import com.droidcourses.designsystem.iconSize
import com.droidcourses.designsystem.R as designR

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SearchBar(
    modifier: Modifier = Modifier,
    text: String,
    isReadOnly: Boolean,
    onSearch: () -> Unit,
    onValueChanged: (String) -> Unit,
    onClick:(() -> Unit)? = null
) {
    val interactionSource = remember {
        MutableInteractionSource()
    }

    val isClicked = interactionSource.collectIsPressedAsState().value
    LaunchedEffect(key1 = isClicked) {
        if (isClicked)
            onClick?.invoke()
    }
    TextField(
        modifier = modifier
            .clickable {
            }
            .searchBarBorder(),
        value = text,
        onValueChange = onValueChanged,
        readOnly = isReadOnly,
        singleLine = true,
        maxLines = 1,
        textStyle = MaterialTheme.typography.bodySmall,
        shape = MaterialTheme.shapes.medium,
        colors = TextFieldDefaults.textFieldColors(
            containerColor = colorResource(id = designR.color.input_background),
            textColor = if (isSystemInDarkTheme()) Color.White else Color.Black,
            cursorColor = if (isSystemInDarkTheme()) Color.White else Color.Black,
            disabledIndicatorColor = Color.Transparent,
            errorIndicatorColor = Color.Transparent,
            focusedIndicatorColor = Color.Transparent,
            unfocusedIndicatorColor = Color.Transparent
        ),
        keyboardOptions = KeyboardOptions(imeAction = ImeAction.Search),
        keyboardActions = KeyboardActions(
            onSearch = {
                onSearch()
            }
        ),
        placeholder = {
            Text(
                text = "Search",
                style = MaterialTheme.typography.bodySmall,
                color = colorResource(id = designR.color.placeholder)
            )
        },
        leadingIcon = {
            Icon(
                painter = painterResource(id = designR.drawable.ic_search),
                contentDescription = null,
                modifier = Modifier.size(iconSize),
                tint = colorResource(id = designR.color.body)
            )
        },
        interactionSource = interactionSource
    )
}


fun Modifier.searchBarBorder()  = composed { composed {
  if (!isSystemInDarkTheme())
      border( width = 1.dp, color = Color.Black, MaterialTheme.shapes.medium )
    else
        this
} }
