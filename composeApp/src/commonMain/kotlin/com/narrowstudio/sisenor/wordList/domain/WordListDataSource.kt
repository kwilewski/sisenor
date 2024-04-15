package com.narrowstudio.sisenor.wordList.domain

import com.narrowstudio.sisenor.word.domain.Word
import kotlinx.coroutines.flow.Flow

interface WordListDataSource {
    fun getWordList(): Flow<List<Word>>
}