package com.narrowstudio.sisenor.word.domain

import com.narrowstudio.sisenor.word.data.SQLDelightWordDataSource
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.emptyFlow
import kotlinx.coroutines.launch

class WordsManager(
    private val wordDataSource: WordDataSource
) {
    companion object {
        var bottomRange: Int = 1
        var topRange: Int = 9489
        var wordList: Flow<List<Word>> = emptyFlow()
    }

    fun setWordsRange(bottom: Int, top: Int) {
        bottomRange = bottom
        topRange = top
    }
    fun getWordsRange(): Pair<Int, Int> {
        return Pair(bottomRange, topRange)
    }

    fun getWordList(): Flow<List<Word>> {
        CoroutineScope(Dispatchers.IO).launch {
            wordList = wordDataSource.getRangeWords(bottomRange.toLong(), topRange.toLong())
        }
        return wordList
    }

}