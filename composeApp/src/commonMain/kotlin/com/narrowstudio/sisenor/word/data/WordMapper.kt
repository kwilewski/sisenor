package com.narrowstudio.sisenor.word.data

import com.narrowstudio.sisenor.word.domain.Word
import comnarrowstudiosisenor.database.WordEntity
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.json.Json

fun WordEntity.toWord(): Word {
    return Word(
        id = id,
        spanishWord = spanishWord,
        englishWord = englishWord,
        isSimilar = isSimilar != 0L,
        isLearned = null,
        audioBytes = null //TODO: get audio
    )
}

fun parseJson(jsonString: String): List<Word> {
    return Json.decodeFromString<List<Word>>(jsonString)
}