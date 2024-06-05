package com.narrowstudio.sisenor.word.domain

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.cancel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import kotlinx.datetime.Clock
import kotlin.native.concurrent.ThreadLocal

class WordsManagerTimer (
    private var triggerTime: Long = 5000L
) {
    private var startTime: Long = 0
    private val _isRunning = MutableStateFlow(false)
    val isRunning = _isRunning.asStateFlow()

    private val _triggerFlow = MutableSharedFlow<Long>()
    val triggerFlow = _triggerFlow.asSharedFlow()

    private val coroutineScope = CoroutineScope(Dispatchers.Default)

    init {
        coroutineScope.launch {
            while (true) {
                if (isRunning.value) {
                    val elapsed = Clock.System.now().toEpochMilliseconds() - startTime
                    if (elapsed >= triggerTime) {
                        resetStartTime()
                        println("triggerFlow emitted")
                        _triggerFlow.emit(Clock.System.now().toEpochMilliseconds())

                    }
                }
                delay(100L)
            }
        }
    }

    fun handleStartPause(){
        if (isRunning.value) {
            pause()
        } else {
            start()
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