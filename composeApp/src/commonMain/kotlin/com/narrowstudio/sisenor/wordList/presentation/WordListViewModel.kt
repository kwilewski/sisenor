package com.narrowstudio.sisenor.wordList.presentation

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import com.narrowstudio.sisenor.wordList.domain.WordList
import dev.icerock.moko.mvvm.viewmodel.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

class WordListViewModel: ViewModel() {
    private val _state = MutableStateFlow(
        WordListState(
        wordLists = wordList
    )
    )
    val state = _state.asStateFlow()


    var selectedBottomRange by mutableStateOf(0f)
    var selectedTopRange by mutableStateOf(13f)

    fun onRangeChanged(range: ClosedFloatingPointRange<Float>){
        selectedBottomRange = range.start
        selectedTopRange = range.endInclusive
    }



    fun onEvent(event: WordListEvent) {

    }



}

private val wordList = (1 .. 20).map{
    WordList(
        id = it.toLong(),
        name = "List nr. $it",
        isSelected = false
    )
}