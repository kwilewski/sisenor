package com.narrowstudio.sisenor.word.presentation

import com.narrowstudio.sisenor.word.domain.Word
import com.narrowstudio.sisenor.word.domain.WordsManager

data class WordState(
    val words: List<Word> = emptyList(),
    val currentWord: Word? = null
)