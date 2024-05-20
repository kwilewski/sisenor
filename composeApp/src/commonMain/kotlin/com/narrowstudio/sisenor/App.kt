package com.narrowstudio.sisenor

import androidx.compose.runtime.*
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import cafe.adriel.voyager.navigator.Navigator
import cafe.adriel.voyager.transitions.SlideTransition
import com.narrowstudio.sisenor.core.presentation.WordListTheme
import com.narrowstudio.sisenor.di.AppModule
import com.narrowstudio.sisenor.screen.home.HomeScreen
import com.narrowstudio.sisenor.screen.wordListSelection.WordListSelectionScreen
import org.jetbrains.compose.resources.ExperimentalResourceApi
import org.jetbrains.compose.ui.tooling.preview.Preview
import org.koin.compose.KoinContext
import org.koin.compose.currentKoinScope


@OptIn(ExperimentalResourceApi::class)
@Composable
@Preview
fun App(
    darkTheme: Boolean,
    dynamicColor: Boolean,
    appModule: AppModule
) {
    WordListTheme(
        darkTheme = darkTheme,
        dynamicColor = dynamicColor
    ){
        KoinContext {
            Navigator(HomeScreen()) { navigator ->
                SlideTransition(navigator)
            }
        }

    }
}

@Composable
inline fun <reified T: ViewModel> koinViewModel(): T {
    val scope = currentKoinScope()
    return viewModel {
        scope.get<T>()
    }
}
