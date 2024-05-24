package com.narrowstudio.sisenor.core.data

import android.content.Context

actual class JSONHandler (
    private val context: Context
){
    actual fun readJSONFile(filename: String): String {
        return context.assets.open(filename).bufferedReader().use { it.readText() }
    }
}