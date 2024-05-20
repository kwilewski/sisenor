package com.narrowstudio.sisenor

import androidx.lifecycle.viewmodel.compose.viewModel
import com.narrowstudio.sisenor.word.presentation.WordViewModel
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module

actual val viewModelModule = module {
    singleOf(::WordViewModel)
}