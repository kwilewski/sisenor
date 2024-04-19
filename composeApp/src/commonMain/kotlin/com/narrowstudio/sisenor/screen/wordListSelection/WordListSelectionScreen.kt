package com.narrowstudio.sisenor.screen.wordListSelection

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBackIosNew
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.MediumTopAppBar
import androidx.compose.material3.RangeSlider
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarColors
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.navigator.LocalNavigator
import com.narrowstudio.sisenor.screen.TopBar
import com.narrowstudio.sisenor.wordList.presentation.WordListEvent
import com.narrowstudio.sisenor.wordList.presentation.WordListScreen
import com.narrowstudio.sisenor.wordList.presentation.WordListViewModel
import dev.icerock.moko.mvvm.compose.getViewModel
import dev.icerock.moko.mvvm.compose.viewModelFactory
import org.jetbrains.compose.resources.ExperimentalResourceApi
import org.jetbrains.compose.resources.stringResource
import org.jetbrains.compose.ui.tooling.preview.Preview
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
            }
        ) {
            Surface(
                modifier = Modifier.fillMaxSize()
                    .padding(it),
                color = MaterialTheme.colorScheme.background,
            ) {
                Column {
                    WordListScreen(
                        state = state,
                        onEvent = viewModel::onEvent,
                        modifier = Modifier.fillMaxHeight(.5f)
                    )
                    RangeSlider(
                        bottomRange = viewModel.bottomRange,
                        topRange = viewModel.topRange,
                        onEvent = { range ->
                            viewModel.onRangeChanged(range)
                        }
                    )
                }
            }
        }
    }

}

@Preview
@Composable
fun RangeSlider(
    bottomRange: Float,
    topRange: Float,
    onEvent:(ClosedFloatingPointRange<Float>) -> Unit
) {
    var sliderPosition by remember { mutableStateOf(bottomRange..topRange) }
    Column (
        modifier = Modifier.padding(24.dp)
    ) {
        Text(text = sliderPosition.toString())
        RangeSlider(
            value = sliderPosition,
            steps = 12,
            onValueChange = { range ->
                sliderPosition = range },
            valueRange = 0f..13f,
            onValueChangeFinished = {
                onEvent(sliderPosition)
            },

        )
    }
}
