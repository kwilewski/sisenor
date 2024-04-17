package com.narrowstudio.sisenor.word.presentation

import com.narrowstudio.sisenor.word.domain.Word
import com.narrowstudio.sisenor.word.domain.WordDataSource
import dev.icerock.moko.mvvm.viewmodel.ViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class WordViewModel(
    private val wordDataSource: WordDataSource
): ViewModel() {

    private val _state = MutableStateFlow(WordState(
        words = words
    ))

    lateinit var state : Flow<WordState>

    init {
        viewModelScope.launch {
            state = combine(
                _state,
                wordDataSource.getWords()
            ){ state, words ->
                state.copy(
                    words = words
                )
            }.stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000L), WordState())
        }
    }




}


private val words = (1 .. 10).map{
    Word(
        id = it.toLong(),
        spanishWord = "spanish word$it",
        englishWord = "english word $it",
        isSimilar = false,
        audioBytes = null
    )
}