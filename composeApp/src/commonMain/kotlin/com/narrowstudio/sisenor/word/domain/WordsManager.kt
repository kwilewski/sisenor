package com.narrowstudio.sisenor.word.domain

import androidx.compose.runtime.collectAsState
import com.narrowstudio.sisenor.core.data.JSONHandler
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.IO
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.emptyFlow
import kotlinx.coroutines.flow.take
import kotlinx.coroutines.flow.toList
import kotlinx.coroutines.launch
import kotlinx.datetime.Clock
import kotlinx.serialization.json.Json
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject
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

        // actual word state to be collected
        val currentWordAsStateFlow = MutableStateFlow(Word(
            id = 0,
            spanishWord = " ",
            englishWord = " ",
            isLearned = false,
            isSimilar = false,
            audioPath = null
        ))

        // timer
        val timer = WordsManagerTimer(
            triggerTime = 5000L
        )
        val timerState = timer.isRunning
    }

    init {
        CoroutineScope(Dispatchers.Default).launch {
            timer.triggerFlow.collect {
                if (timer.isRunning.value) {
                    println("Triggering")
                    getNextWord()
                }
            }
        }
    }

    // setting range of words from listSelection
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


    fun insertDataFromJSONFile(){
        val jsonHandler: JSONHandler by inject()
        CoroutineScope(Dispatchers.IO).launch {
            // reading string from JSON file
            val jsonString = jsonHandler.readJSONFile("words.json")
            val json = Json {
                ignoreUnknownKeys = true
                isLenient = true
                encodeDefaults = true
            }
            var wordList: List<Word> = emptyList()
            // converting string to list of words
            try {
                wordList = json.decodeFromString(jsonString)
            }catch (e: RuntimeException){
                println("Error: ${e.message}")
            }
            // inserting words into database
            for (word in wordList) {
                wordDataSource.insertWord(word)
            }
        }
    }

    // emitting new word whenever needed
    private fun emitNewWordStateFlowValue(){
        currentWordAsStateFlow.value = wordList[currentWordIndex]
    }

    // loading words from database
    @OptIn(ExperimentalCoroutinesApi::class)
    fun getWordListFromDB() {
        println("Starting getWordListFromDB from ${bottomRange.toLong()} to ${topRange.toLong()}")
        CoroutineScope(Dispatchers.IO).launch {
            println("Launching coroutine")
            wordListAsFlow = wordDataSource.getRangeWords(bottomRange.toLong(), topRange.toLong())
            println("Flow created")
            wordList = wordListAsFlow.take(1).toList().flatten()
            println("flattened. Size of wordList: ${wordList.size}")
            wordList = wordList.shuffled(Random(Clock.System.now().toEpochMilliseconds()))
            println("shuffled")
            println("Size of wordList: ${wordList.size}")
            // setting max index to avoid NullPointerException
            setMaxIndex()
            emitNewWordStateFlowValue()
        }
    }

    fun onPlayPauseButtonClicked() {
        if (timer.isRunning.value) {
            timer.pause()
            println("Timer paused")
        } else {
            timer.start()
            println("Timer started")
        }
    }


    fun getCurrentWord(): Word {
        return wordList[currentWordIndex]
    }

    // setting previous word and emitting it in stateFlow
    fun getPreviousWord() {
        if (wordList.isEmpty()) {
            getWordsIfEmpty()
        } else {
            setPreviousIndex()
            emitNewWordStateFlowValue()
            timer.resetStartTime()
            println(wordList[currentWordIndex])
        }
    }

    // setting next word and emitting it in stateFlow
    fun getNextWord() {
        if (wordList.isEmpty()) {
            getWordsIfEmpty()
        } else {
            setNextIndex()
            emitNewWordStateFlowValue()
            timer.resetStartTime()
            println(wordList[currentWordIndex])
        }
    }



    private fun setMaxIndex(){
        maxWordIndex = wordList.size - 1
    }

    // loading words from database if list is empty
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

    // returning flow of Word
    fun getCurrentWordAsFlow(): Flow<Word> {
        return currentWordAsStateFlow.asStateFlow()
    }

    fun getTimerState(): Flow<Boolean> {
        return timerState
    }



}