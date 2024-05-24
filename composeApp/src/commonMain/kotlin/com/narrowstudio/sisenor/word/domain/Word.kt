package com.narrowstudio.sisenor.word.domain

import kotlinx.serialization.Serializable
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.json.Json

@Serializable
data class Word(
    val id: Long,
    val spanishWord: String,
    val englishWord: String,
    val isSimilar: Boolean,
    val isLearned: Boolean?,
    val audioBytes: ByteArray?
)

