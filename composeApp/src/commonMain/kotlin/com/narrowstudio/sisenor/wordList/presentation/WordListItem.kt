package com.narrowstudio.sisenor.wordList.presentation

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.narrowstudio.sisenor.wordList.domain.WordList

@Composable
fun WordListItem (
    wordList: WordList,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier,
        verticalAlignment = Alignment.CenterVertically
    ) {
        WordListSelectionIcon(
            wordList = wordList,
            modifier = Modifier.size(50.dp)
        )

        Spacer(Modifier.width(16.dp))

        Text(
            text = wordList.name,
            modifier = Modifier.weight(1f)
        )
    }
}