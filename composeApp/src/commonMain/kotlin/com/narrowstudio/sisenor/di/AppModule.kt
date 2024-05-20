package com.narrowstudio.sisenor.di

import com.narrowstudio.sisenor.word.domain.WordDataSource
import org.koin.core.module.Module

expect class AppModule {
    val wordDataSource: WordDataSource
}

expect val dataSourceModule: Module