package com.narrowstudio.sisenor.wordList.presentation

import com.narrowstudio.sisenor.wordList.domain.WordList

sealed interface WordListEvent {
    data class OnWordListClick(val wordList: WordList) : WordListEvent
    class OnWordSelectionChanged(val range: ClosedFloatingPointRange<Float>): WordListEvent
}