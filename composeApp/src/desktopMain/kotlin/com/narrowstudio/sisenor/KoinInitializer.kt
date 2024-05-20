package com.narrowstudio.sisenor

import com.narrowstudio.sisenor.di.dataSourceModule
import org.koin.core.context.startKoin

actual class KoinInitializer {
    actual fun init() {
        startKoin {
            modules(listOf(viewModelModule, dataSourceModule))
        }
    }

}