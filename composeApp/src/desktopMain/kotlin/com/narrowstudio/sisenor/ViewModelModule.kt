package com.narrowstudio.sisenor


import com.narrowstudio.sisenor.word.presentation.WordViewModel
import com.narrowstudio.sisenor.wordList.presentation.WordListViewModel
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module

actual val viewModelModule = module {
    singleOf(::WordViewModel)
    singleOf(::WordListViewModel)
}