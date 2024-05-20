package com.narrowstudio.sisenor

import com.narrowstudio.sisenor.word.presentation.WordViewModel
import org.koin.androidx.viewmodel.dsl.viewModelOf
import org.koin.dsl.module

actual val viewModelModule = module {
    viewModelOf(::WordViewModel)
}