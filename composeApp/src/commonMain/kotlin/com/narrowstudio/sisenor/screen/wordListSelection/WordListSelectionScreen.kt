package com.narrowstudio.sisenor.screen.wordListSelection

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.navigator.LocalNavigator
import com.narrowstudio.sisenor.wordList.presentation.WordListScreen
import com.narrowstudio.sisenor.wordList.presentation.WordListViewModel
import dev.icerock.moko.mvvm.compose.getViewModel
import dev.icerock.moko.mvvm.compose.viewModelFactory

class WordListSelectionScreen: Screen {
    @Composable
    override fun Content() {
        val navigator = LocalNavigator.current
        val viewModel = getViewModel(
            key = "word-list-screen",
            factory = viewModelFactory {
                WordListViewModel()
            }
        )
        val state by viewModel.state.collectAsState()
        Surface(
            modifier = Modifier.fillMaxSize(),
            color = MaterialTheme.colorScheme.background
        ) {
            WordListScreen(
                state = state,
                onEvent = viewModel::onEvent
            )
        }
    }
}