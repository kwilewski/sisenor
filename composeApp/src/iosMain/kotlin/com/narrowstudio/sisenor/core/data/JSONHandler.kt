package com.narrowstudio.sisenor.core.data


actual class JSONHandler {
    actual fun readJSONFile(filename: String): String {
        val filePath = NSBundle.mainBundle.pathForResource(filename, ofType = "json")!!
        return NSString.stringWithContentsOfFile(filePath, NSUTF8StringEncoding, null) as String
    }
}