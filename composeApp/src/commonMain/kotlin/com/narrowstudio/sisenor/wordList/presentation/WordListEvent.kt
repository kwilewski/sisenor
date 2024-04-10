package com.narrowstudio.sisenor.wordList.presentation

sealed interface WordListEvent {
    object OnWordListClick: WordListEvent
}