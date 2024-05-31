package com.narrowstudio.sisenor.word.domain

import kotlinx.serialization.Serializable
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.json.Json

@Serializable
data class Word(
    val id: Long,
    val spanishWord: String,
    val englishWord: String,
    @Serializable(with = BooleanAsIntSerializer::class)
    val isSimilar: Boolean,
    @Serializable(with = NullableBooleanAsIntSerializer::class)
    val isLearned: Boolean?,
    val audioPath: String?
)

