package com.narrowstudio.sisenor.di

import android.content.Context
import com.narrowstudio.sisenor.core.data.JSONHandler
import com.narrowstudio.sisenor.word.domain.WordDataSource
import org.koin.dsl.module
import org.koin.android.ext.koin.androidContext

val androidModule = module {
    single { JSONHandler(androidContext()) }
}