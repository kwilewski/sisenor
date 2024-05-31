package com.narrowstudio.sisenor.word.domain

import com.narrowstudio.sisenor.core.data.JSONHandler
import com.narrowstudio.sisenor.word.data.parseJson
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.IO
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.asFlow
import kotlinx.coroutines.flow.emptyFlow
import kotlinx.coroutines.flow.flatMapConcat
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.take
import kotlinx.coroutines.flow.toList
import kotlinx.coroutines.launch
import kotlinx.datetime.Clock
import kotlinx.datetime.toLocalDateTime
import kotlinx.serialization.SerializationException
import kotlinx.serialization.json.Json
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject
import org.koin.core.logger.Logger
import kotlin.random.Random

class WordsManager(
    private val wordDataSource: WordDataSource
): KoinComponent {
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

    suspend fun insertDataFromJSONFile(){
        val jsonHandler: JSONHandler by inject()
        val jsonString = jsonHandler.readJSONFile("files/json/words.json")
        val json = Json { ignoreUnknownKeys = true }
        var wordList: List<Word> = emptyList()
        try {
            wordList = json.decodeFromString(jsonString)
        }catch (E: RuntimeException){
            println("Error: ${E.message}")
        }
        for (word in wordList) {
            wordDataSource.insertWord(word)
        }
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    fun getWordListFromDB() {
        println("Starting getWordListFromDB from ${bottomRange.toLong()} to ${topRange.toLong()}")
        CoroutineScope(Dispatchers.IO).launch {
            println("Launching coroutine")
            wordListAsFlow = wordDataSource.getRangeWords(bottomRange.toLong(), topRange.toLong())
            println("Flow created")
            wordList = wordListAsFlow.take(1).toList().flatten()
            println("flattened. Size of wordList: ${wordList.size}")
            wordList.shuffled(Random(Clock.System.now().toEpochMilliseconds()))
            println("shuffled")
            println("Size of wordList: ${wordList.size}")
        }
    }

    fun getCurrentWord(): Word {
        return wordList[currentWordIndex]
    }

    fun getPreviousWord(): Word {
        getWordsIfEmpty()
        setPreviousIndex()
        return wordList[currentWordIndex]
    }

    fun getNextWord(): Word? {
        getWordsIfEmpty()
        setNextIndex()
//        return wordList[currentWordIndex]
        return null
    }

    private fun getWordsIfEmpty(){
        if (wordList.isEmpty()) getWordListFromDB()
    }

    private fun setPreviousIndex() {
        if (currentWordIndex > 0) currentWordIndex--
        else currentWordIndex = maxWordIndex
    }

    private fun setNextIndex() {
        if (currentWordIndex < maxWordIndex) currentWordIndex++
        else currentWordIndex = 0
    }

    fun getWordsAsFlow(): Flow<List<Word>> {
        return wordListAsFlow
    }


}