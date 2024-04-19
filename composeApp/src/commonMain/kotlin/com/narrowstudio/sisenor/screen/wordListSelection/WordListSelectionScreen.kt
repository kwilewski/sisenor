package com.narrowstudio.sisenor.screen.wordListSelection

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBackIosNew
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.MediumTopAppBar
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarColors
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.navigator.LocalNavigator
import com.narrowstudio.sisenor.screen.TopBar
import com.narrowstudio.sisenor.wordList.presentation.WordListScreen
import com.narrowstudio.sisenor.wordList.presentation.WordListViewModel
import dev.icerock.moko.mvvm.compose.getViewModel
import dev.icerock.moko.mvvm.compose.viewModelFactory
import org.jetbrains.compose.resources.ExperimentalResourceApi
import org.jetbrains.compose.resources.stringResource
import sisenor.composeapp.generated.resources.Res
import sisenor.composeapp.generated.resources.selection_top_bar

class WordListSelectionScreen: Screen {
    @OptIn(ExperimentalMaterial3Api::class, ExperimentalResourceApi::class)
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


        Scaffold (
            topBar = {
                TopBar(
                    title = stringResource(Res.string.selection_top_bar),
                    onNavigationClick = {
                        navigator?.pop()
                    },
                    onSettingClick = {
                        //TODO
                    }
                )
//                TopAppBar(
//                    title = {
//                        Text(stringResource(Res.string.selection_top_bar))
//                    },
//                    colors = TopAppBarDefaults.topAppBarColors(
//                        containerColor = MaterialTheme.colorScheme.primaryContainer,
//                        titleContentColor = MaterialTheme.colorScheme.onPrimaryContainer,
//                        navigationIconContentColor = MaterialTheme.colorScheme.onPrimaryContainer),
//                    navigationIcon = {
//                        IconButton(onClick = {
//                            navigator?.pop()
//                        }) {
//                            Icon(
//                                imageVector = Icons.Default.ArrowBackIosNew,
//                                contentDescription = "Back arrow"
//                            )
//                        }
//                    }
//                )
            }
        ){
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

}
