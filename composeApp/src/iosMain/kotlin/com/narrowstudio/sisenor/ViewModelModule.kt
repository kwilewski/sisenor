package com.narrowstudio.sisenor

import com.narrowstudio.sisenor.word.presentation.WordViewModel
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module

actual val viewModelModule = module{
    singleOf(::WordViewModel)
}