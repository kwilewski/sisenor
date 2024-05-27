package com.narrowstudio.sisenor

import android.content.Context
import com.narrowstudio.sisenor.di.AppModule
import com.narrowstudio.sisenor.di.androidModule
import com.narrowstudio.sisenor.di.dataSourceModule
import com.narrowstudio.sisenor.word.domain.WordDataSource
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module

actual class KoinInitializer(
    private val context: Context
) {
    actual fun init() {
        startKoin {
            androidContext(context)
            androidLogger()
            modules(viewModelModule, dataSourceModule, androidModule)
        }
    }

}

