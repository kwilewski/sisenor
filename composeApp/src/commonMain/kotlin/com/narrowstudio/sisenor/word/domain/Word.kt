package com.narrowstudio.sisenor.word.domain

data class Word(
    val id: Long,
    val spanishWord: String,
    val englishWord: String,
    val audioBytes: ByteArray?
)
