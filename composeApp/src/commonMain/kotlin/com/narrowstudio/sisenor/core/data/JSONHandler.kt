package com.narrowstudio.sisenor.core.data

expect class JSONHandler {
    fun readJSONFile(filename:String): String
}