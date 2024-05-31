package com.narrowstudio.sisenor.core.data

import android.content.Context
import android.util.Log
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.decodeFromJsonElement
import kotlinx.serialization.json.decodeFromStream
import java.io.FileNotFoundException

actual class JSONHandler (
    private val context: Context
){
    actual fun readJSONFile(filename: String): String {
        try {
            return Json.decodeFromStream(context.assets.open(filename))
            //return context.assets.open(filename).bufferedReader().use { it.readText() }
        } catch (e: FileNotFoundException) {
            Log.d("JSONHandler", "File not found: $filename")
            return "[{\"id\":121,\"spanishWord\":\"de\",\"englishWord\":\"of, from\",\"isSimilar\":0,\"isLearned\":0,\"audioPath\":\"audio\"}]"
        }
    }
}