package com.narrowstudio.sisenor.di

import com.narrowstudio.sisenor.core.data.DatabaseDriverFactory
import com.narrowstudio.sisenor.database.WordDatabase
import com.narrowstudio.sisenor.word.data.SQLDelightWordDataSource
import com.narrowstudio.sisenor.word.domain.WordDataSource
import org.koin.dsl.module

actual class AppModule {
    actual val wordDataSource: WordDataSource by lazy {
        SQLDelightWordDataSource(
            db = WordDatabase(
                driver = DatabaseDriverFactory().create()
            )
        )
    }
}

actual val dataSourceModule = module {
    single<WordDataSource> { AppModule().wordDataSource }
}