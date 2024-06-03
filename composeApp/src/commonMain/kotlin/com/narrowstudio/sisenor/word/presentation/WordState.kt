package com.narrowstudio.sisenor.word.presentation

import com.narrowstudio.sisenor.word.domain.Word
import com.narrowstudio.sisenor.word.domain.WordsManager

data class WordState(
    val words: List<Word> = emptyList(),
    var currentWord: Word? = Word(
        id = 3,
        spanishWord = "puta",
        englishWord = "madre",
        isLearned = false,
        isSimilar = false,
        audioPath = null
    )
)