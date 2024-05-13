package com.narrowstudio.sisenor.word.domain

import com.narrowstudio.sisenor.word.data.SQLDelightWordDataSource

object WordsManager {
    var bottomRange: Int = 1
    var topRange: Int = 9489
    var wordList: List<Word> = emptyList()

    fun setWordsRange(bottom: Int, top: Int){
        bottomRange = bottom
        topRange = top
    }

    fun getWordList(){

    }
}