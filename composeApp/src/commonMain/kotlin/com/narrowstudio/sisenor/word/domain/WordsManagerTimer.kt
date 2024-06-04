package com.narrowstudio.sisenor.word.domain

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
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
    private var isRunning: Boolean = false

    private val _triggerFlow = MutableStateFlow(triggerTime)
    val triggerFlow = _triggerFlow.asStateFlow()


    fun start() {
        if (!isRunning) {
            startTime = Clock.System.now().toEpochMilliseconds()
            isRunning = true
            tick()
        }
    }

    fun pause() {
        isRunning = false
    }

    private fun tick() {
        if (isRunning) {
            CoroutineScope(Dispatchers.Default).launch {
                val elapsed = Clock.System.now().toEpochMilliseconds() - startTime
                if (elapsed >= triggerTime) {
                    _triggerFlow.value = elapsed
                    pause()
                    return@launch
                }
                delay(100L)
                tick()
            }
        }
    }
}