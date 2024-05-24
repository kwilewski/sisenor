package com.narrowstudio.sisenor.core.data

import android.content.Context
import app.cash.sqldelight.db.SqlDriver
import app.cash.sqldelight.driver.android.AndroidSqliteDriver
import com.narrowstudio.sisenor.database.WordDatabase
import java.io.File

actual class DatabaseDriverFactory(
    private val context: Context
) {
    actual fun create(): SqlDriver {
        return AndroidSqliteDriver(
            WordDatabase.Schema,
            context,
            "words.db"
        )
    }
}