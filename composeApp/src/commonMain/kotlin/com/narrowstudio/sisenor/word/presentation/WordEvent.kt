package com.narrowstudio.sisenor.word.presentation

sealed interface WordEvent {
    class onStartClick(): WordEvent
    class onPreviousClick(): WordEvent
    class onNextClick(): WordEvent
    class onMarkedAsLearnedClick(): WordEvent
    class onOpenInFloatingWindowClick(): WordEvent
}