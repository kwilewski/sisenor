package com.narrowstudio.sisenor.screen.wordListSelection

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.rounded.ArrowForward
import androidx.compose.material.icons.filled.ArrowBackIosNew
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
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
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
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
import sisenor.composeapp.generated.resources.selected_words
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
        val selectedBottomRange by viewModel.selectedBottomRangeProcessed.collectAsState()
        val selectedTopRange by viewModel.selectedTopRangeProcessed.collectAsState()


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
                    Column(
                        modifier = Modifier
                            .fillMaxSize(),
                        verticalArrangement = Arrangement.Center,
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        RangeSlider(
                            bottomRange = viewModel.bottomRange,
                            topRange = viewModel.topRange,
                            selectedBottomRange = selectedBottomRange,
                            selectedTopRange = selectedTopRange,
                            onEvent = { range ->
                                viewModel.onRangeChanged(range)
                            }
                        )
                        Spacer(modifier = Modifier.size(70.dp))
                        Button(
                            onClick = {
                                // TODO
                            },
                            modifier = Modifier.height(70.dp)
                                .width(70.dp),
                            shape = RoundedCornerShape(50),
                            //colors = ButtonDefaults.buttonColors(MaterialTheme.colorScheme.primary)
                        ) {
                            Icon(
                                Icons.AutoMirrored.Rounded.ArrowForward,
                                contentDescription = "Confirm",
                                tint = MaterialTheme.colorScheme.onPrimary,
                                modifier = Modifier.size(36.dp)
                            )
                        }
                    }

                }
            }
        }

}

@OptIn(ExperimentalResourceApi::class)
@Preview
@Composable
fun RangeSlider(
    bottomRange: Float,
    topRange: Float,
    selectedBottomRange: Int,
    selectedTopRange: Int,
    onEvent:(ClosedFloatingPointRange<Float>) -> Unit
) {
    var sliderPosition by remember { mutableStateOf(bottomRange..topRange) }
    Column (
        modifier = Modifier.padding(40.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = stringResource(Res.string.selected_words, selectedBottomRange, selectedTopRange),
            modifier = Modifier.fillMaxWidth(.8f),
            textAlign = TextAlign.Center,
            fontSize = 36.sp,
            lineHeight = 50.sp,
        )
        Spacer(modifier = Modifier.size(150.dp))
        RangeSlider(
            value = sliderPosition,
            steps = 11,
            onValueChange = { range ->
                sliderPosition = range
                onEvent(sliderPosition)},
            valueRange = 0f..12f,
            onValueChangeFinished = {
                onEvent(sliderPosition)
            },

        )
    }
}
