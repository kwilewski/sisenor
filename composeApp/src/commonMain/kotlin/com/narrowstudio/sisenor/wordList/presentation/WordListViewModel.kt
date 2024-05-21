package com.narrowstudio.sisenor.wordList.presentation


import androidx.lifecycle.ViewModel
import com.narrowstudio.sisenor.word.domain.WordDataSource
import com.narrowstudio.sisenor.word.domain.WordsManager
import com.narrowstudio.sisenor.wordList.domain.CONST_WORD_LIST_RANGE_SECTIONS
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlin.math.roundToInt

class WordListViewModel(
    private val wordDataSource: WordDataSource
): ViewModel() {
    private val _state = MutableStateFlow(
        WordListState(
    )
    )
    val state = _state.asStateFlow()

    var bottomRange: Float = 0f
    var topRange: Float = 12f

    private val _selectedBottomRangeProcessed = MutableStateFlow(CONST_WORD_LIST_RANGE_SECTIONS[0] + 1)
    val selectedBottomRangeProcessed = _selectedBottomRangeProcessed.asStateFlow()

    private val _selectedTopRangeProcessed = MutableStateFlow(CONST_WORD_LIST_RANGE_SECTIONS[CONST_WORD_LIST_RANGE_SECTIONS.size - 1])
    val selectedTopRangeProcessed = _selectedTopRangeProcessed.asStateFlow()

    fun onRangeChanged(range: ClosedFloatingPointRange<Float>){
        if (range.start != range.endInclusive) {
            bottomRange = range.start
            topRange = range.endInclusive
            processRangeChanged()
        }
    }

    private fun processRangeChanged(){
        _selectedBottomRangeProcessed.value = CONST_WORD_LIST_RANGE_SECTIONS[bottomRange.roundToInt()] + 1
        _selectedTopRangeProcessed.value = CONST_WORD_LIST_RANGE_SECTIONS[topRange.roundToInt()]
        sendWordListRange()
    }

    private fun sendWordListRange(){
        WordsManager(wordDataSource).setWordsRange(
            bottom = _selectedBottomRangeProcessed.value,
            top = _selectedTopRangeProcessed.value
        )
    }



    fun onEvent(event: WordListEvent) {

    }



}
