package com.narrowstudio.sisenor

import androidx.compose.runtime.*
import cafe.adriel.voyager.navigator.Navigator
import cafe.adriel.voyager.transitions.SlideTransition
import com.narrowstudio.sisenor.core.presentation.WordListTheme
import com.narrowstudio.sisenor.screen.home.HomeScreen
import com.narrowstudio.sisenor.screen.wordListSelection.WordListSelectionScreen
import org.jetbrains.compose.resources.ExperimentalResourceApi
import org.jetbrains.compose.ui.tooling.preview.Preview


@OptIn(ExperimentalResourceApi::class)
@Composable
@Preview
fun App(
    darkTheme: Boolean,
    dynamicColor: Boolean
) {
    WordListTheme(
        darkTheme = darkTheme,
        dynamicColor = dynamicColor
    ){
        Navigator(HomeScreen()) {navigator ->
            SlideTransition(navigator)
        }

    }
}

