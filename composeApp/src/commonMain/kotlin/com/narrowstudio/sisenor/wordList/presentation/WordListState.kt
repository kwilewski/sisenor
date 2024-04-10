package com.narrowstudio.sisenor.wordList.presentation

import com.narrowstudio.sisenor.wordList.domain.WordList

data class WordListState(
    val wordLists: List<WordList> = emptyList(),
    val selectedWordLists: List<WordList> = emptyList()
)
