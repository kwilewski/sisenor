package com.narrowstudio.sisenor.word.presentation

import com.narrowstudio.sisenor.word.domain.Word
import com.narrowstudio.sisenor.word.domain.WordDataSource
import com.narrowstudio.sisenor.word.domain.WordsManager
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

    private val _state = MutableStateFlow(WordState())

    lateinit var state : Flow<WordState>



    init {
        viewModelScope.launch {
            state = combine(
                _state,
                wordDataSource.getRangeWords(WordsManager.bottomRange.toLong(), WordsManager.topRange.toLong())
            ){ state, words ->
                state.copy(
                    words = words
                )
            }.stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000L), WordState())
        }
    }


    fun onEvent(event: WordEvent) {
        when(event) {
            is WordEvent.onNextClick -> TODO()
            is WordEvent.onPreviousClick -> TODO()
            is WordEvent.onStartClick -> TODO()
        }
    }


}
