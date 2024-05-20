package com.narrowstudio.sisenor.core.data

import app.cash.sqldelight.db.SqlDriver
import app.cash.sqldelight.driver.native.NativeSqliteDriver
import com.narrowstudio.sisenor.database.WordDatabase

actual class DatabaseDriverFactory {
    actual fun create(): SqlDriver {
        return NativeSqliteDriver(WordDatabase.Schema, "word.db")
    }

}