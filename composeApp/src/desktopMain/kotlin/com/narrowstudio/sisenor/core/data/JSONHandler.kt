package com.narrowstudio.sisenor.core.data

import java.io.File

actual class JSONHandler {
    actual fun readJSONFile(filename: String): String {
        return File(filename).readText()
    }
}