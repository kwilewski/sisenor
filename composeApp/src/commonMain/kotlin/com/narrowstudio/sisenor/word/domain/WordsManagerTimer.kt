package com.narrowstudio.sisenor.word.domain

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.cancel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import kotlinx.datetime.Clock

class WordsManagerTimer (
    private var triggerTime: Long = 5000L
) {
    private var startTime: Long = 0
    private val _isRunning = MutableStateFlow(false)
    val isRunning = _isRunning.asStateFlow()

    private val _triggerFlow = MutableStateFlow(triggerTime)
    val triggerFlow = _triggerFlow.asStateFlow()

    private val coroutineScope = CoroutineScope(Dispatchers.Default)

    init {
        coroutineScope.launch {
            while (true) {
                if (isRunning.value) {
                    val elapsed = Clock.System.now().toEpochMilliseconds() - startTime
                    if (elapsed >= triggerTime) {
                        resetStartTime()
                        _triggerFlow.value = Clock.System.now().toEpochMilliseconds()
                    }
                }
                delay(100L)
            }
        }
    }


    fun start() {
        if (!isRunning.value) {
            resetStartTime()
            _isRunning.value = true
        }
    }

    fun pause() {
        _isRunning.value = false
    }

    fun resetStartTime() {
        startTime = Clock.System.now().toEpochMilliseconds()
    }

}