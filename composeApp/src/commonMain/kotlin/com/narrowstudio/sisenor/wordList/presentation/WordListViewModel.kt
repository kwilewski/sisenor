package com.narrowstudio.sisenor.wordList.presentation

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import com.narrowstudio.sisenor.wordList.domain.CONST_WORD_LIST_RANGE_SECTIONS
import com.narrowstudio.sisenor.wordList.domain.WordList
import dev.icerock.moko.mvvm.viewmodel.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlin.math.roundToInt

class WordListViewModel: ViewModel() {
    private val _state = MutableStateFlow(
        WordListState(
        wordLists = wordList
    )
    )
    val state = _state.asStateFlow()

    var bottomRange: Float = 0f
    var topRange: Float = 12f

    private val _selectedBottomRangeProcessed = MutableStateFlow(CONST_WORD_LIST_RANGE_SECTIONS[0])
    val selectedBottomRangeProcessed = _selectedBottomRangeProcessed.asStateFlow()

    private val _selectedTopRangeProcessed = MutableStateFlow(CONST_WORD_LIST_RANGE_SECTIONS[CONST_WORD_LIST_RANGE_SECTIONS.size - 1])
    val selectedTopRangeProcessed = _selectedTopRangeProcessed.asStateFlow()

    fun onRangeChanged(range: ClosedFloatingPointRange<Float>){
        bottomRange = range.start
        topRange = range.endInclusive
        processRangeChanged()
    }

    private fun processRangeChanged(){
        _selectedBottomRangeProcessed.value = CONST_WORD_LIST_RANGE_SECTIONS[bottomRange.roundToInt()]
        _selectedTopRangeProcessed.value = CONST_WORD_LIST_RANGE_SECTIONS[topRange.roundToInt()]
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
