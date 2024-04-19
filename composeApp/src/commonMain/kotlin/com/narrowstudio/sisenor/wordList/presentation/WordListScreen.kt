package com.narrowstudio.sisenor.wordList.presentation

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.narrowstudio.sisenor.wordList.domain.WordList

@Composable
fun WordListScreen(
    state: WordListState,
    onEvent: (WordListEvent) -> Unit,
    modifier: Modifier = Modifier
) {
    Column (
        modifier = modifier
    ){
        Text(
            text = "Most popular words",
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            fontWeight = FontWeight.Bold
        )
        LazyColumn(
            modifier = Modifier.fillMaxWidth(),
            contentPadding = PaddingValues(vertical = 16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            items(state.wordLists) { wordList: WordList ->
                WordListItem(
                    wordList = wordList,
                    modifier = Modifier
                        .fillMaxWidth()
                        .clickable {
                            onEvent(WordListEvent.OnWordListClick(wordList))
                        }
                        .padding(16.dp)
                )
            }
        }
    }
}
