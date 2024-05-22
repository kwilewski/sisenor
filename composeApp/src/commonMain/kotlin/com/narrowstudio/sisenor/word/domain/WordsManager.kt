package com.narrowstudio.sisenor.word.domain

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.asFlow
import kotlinx.coroutines.flow.emptyFlow
import kotlinx.coroutines.flow.flatMapConcat
import kotlinx.coroutines.flow.toList
import kotlinx.coroutines.launch
import org.jetbrains.skiko.currentNanoTime
import kotlin.random.Random

class WordsManager(
    private val wordDataSource: WordDataSource
) {
    companion object {
        var bottomRange: Int = 1
        var topRange: Int = 9489
        var wordListAsFlow: Flow<List<Word>> = emptyFlow()
        var wordList: List<Word> = emptyList()
        var currentWordIndex = 0
        var maxWordIndex = 0
    }

    fun setWordsRange(bottom: Int, top: Int) {
        if (bottomRange != bottom || topRange != top) {
            bottomRange = bottom
            topRange = top
            currentWordIndex = 0
            maxWordIndex = topRange - bottomRange
        }
    }

    fun getWordsRange(): Pair<Int, Int> {
        return Pair(bottomRange, topRange)
    }

    fun getWordListFromDB() {
        CoroutineScope(Dispatchers.IO).launch {
            wordListAsFlow = wordDataSource.getRangeWords(bottomRange.toLong(), topRange.toLong())
            wordList = wordListAsFlow.flatMapConcat { it.asFlow() }.toList()
            wordList.shuffled(Random(currentNanoTime()))
        }
    }

    fun getCurrentWord(): Word {
        return wordList[currentWordIndex]
    }

    fun getPreviousWord(): Word {
        setPreviousIndex()
        return wordList[currentWordIndex]
    }

    fun getNextWord(): Word {
        setNextIndex()
        return wordList[currentWordIndex]
    }



    private fun setPreviousIndex() {
        if (currentWordIndex > 0) currentWordIndex--
        else currentWordIndex = maxWordIndex
    }

    private fun setNextIndex() {
        if (currentWordIndex < maxWordIndex) currentWordIndex++
        else currentWordIndex = 0
    }


}