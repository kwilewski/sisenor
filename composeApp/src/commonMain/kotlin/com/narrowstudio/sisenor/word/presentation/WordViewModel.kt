package com.narrowstudio.sisenor.word.presentation

import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.narrowstudio.sisenor.word.domain.Word
import com.narrowstudio.sisenor.word.domain.WordDataSource
import com.narrowstudio.sisenor.word.domain.WordsManager
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch


class WordViewModel(
    private val wordDataSource: WordDataSource
): ViewModel() {

    private val _state = MutableStateFlow(WordState())
    private val wordsManager = WordsManager(wordDataSource)

    var state = combine(
        _state,
        wordsManager.getWordsAsFlow()
    ){ state, words ->
        state.copy(
            words = words,
            currentWord = words.first()
        )
    }.stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000L), WordState())

    val stateFromWM = wordsManager.getCurrentWordAsFlow()

    val timerState = wordsManager.getTimerState()


    var currentWordState by mutableStateOf(Word(
        id = 0,
        spanishWord = " ",
        englishWord = " ",
        isLearned = false,
        isSimilar = false,
        audioPath = null
    ))
        private set



    init {
        WordsManager(wordDataSource).getWordListFromDB()
        viewModelScope.launch {
            delay(3000L)
            currentWordState = WordsManager(wordDataSource).getCurrentWord()
        }
        collectWordFlow()
    }

    private fun collectWordFlow(){
        viewModelScope.launch {

        }
    }


    fun onEvent(event: WordEvent) {
        when(event) {
            is WordEvent.onNextClick -> {
                wordsManager.getNextWord()
            }
            is WordEvent.onPreviousClick -> {
                wordsManager.getPreviousWord()
            }
            is WordEvent.onStartClick -> {
                wordsManager.onPlayPauseButtonClicked()
            }
            is WordEvent.onMarkedAsLearnedClick -> TODO()
            is WordEvent.onOpenInFloatingWindowClick -> TODO()
        }
    }


}
