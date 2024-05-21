package com.narrowstudio.sisenor

import com.narrowstudio.sisenor.word.presentation.WordViewModel
import com.narrowstudio.sisenor.wordList.presentation.WordListViewModel
import org.koin.androidx.viewmodel.dsl.viewModelOf
import org.koin.dsl.module

actual val viewModelModule = module {
    viewModelOf(::WordViewModel)
    viewModelOf(::WordListViewModel)
}