package com.narrowstudio.sisenor.word.presentation

import com.narrowstudio.sisenor.word.domain.Word

data class WordState(
    val words: List<Word> = emptyList()
)