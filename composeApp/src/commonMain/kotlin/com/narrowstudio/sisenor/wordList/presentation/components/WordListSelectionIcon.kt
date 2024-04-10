package com.narrowstudio.sisenor.wordList.presentation.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Check
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.narrowstudio.sisenor.wordList.domain.WordList

@Composable
fun WordListSelectionIcon(
    wordList: WordList?,
    modifier: Modifier = Modifier,
    iconSize: Dp = 25.dp
){
    val iconModifier = modifier.clip(RoundedCornerShape(50))
    if (wordList?.isSelected == true){
        Box(
            modifier = iconModifier
                .background(MaterialTheme.colorScheme.secondaryContainer),
            contentAlignment = Alignment.Center
        ) {
            Icon(
                imageVector = Icons.Rounded.Check,
                contentDescription = wordList.name,
                modifier = Modifier.size(iconSize),
                tint = MaterialTheme.colorScheme.onSecondaryContainer
            )
        }
    } else {
        Box(
            modifier = iconModifier
                .background(MaterialTheme.colorScheme.secondaryContainer)
        )
    }

}