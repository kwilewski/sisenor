package com.narrowstudio.sisenor.di

import android.content.Context
import com.narrowstudio.sisenor.core.data.DatabaseDriverFactory
import com.narrowstudio.sisenor.database.WordDatabase
import com.narrowstudio.sisenor.word.data.SQLDelightWordDataSource
import com.narrowstudio.sisenor.word.domain.WordDataSource
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module

actual class AppModule(
    private val context: Context
) {
    actual val wordDataSource: WordDataSource by lazy {
        SQLDelightWordDataSource(
            db = WordDatabase(
                driver = DatabaseDriverFactory(context).create()
            )
        )
    }


}

actual val dataSourceModule = module {
    single<WordDataSource> { AppModule(get()).wordDataSource }
}