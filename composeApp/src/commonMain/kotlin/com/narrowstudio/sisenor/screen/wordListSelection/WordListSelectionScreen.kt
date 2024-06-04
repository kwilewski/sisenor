package com.narrowstudio.sisenor.screen.wordListSelection

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.rounded.ArrowForward
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.RangeSlider
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.windowsizeclass.ExperimentalMaterial3WindowSizeClassApi
import androidx.compose.material3.windowsizeclass.WindowWidthSizeClass
import androidx.compose.material3.windowsizeclass.calculateWindowSizeClass
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.navigator.LocalNavigator
import com.narrowstudio.sisenor.koinViewModel
import com.narrowstudio.sisenor.screen.TopBar
import com.narrowstudio.sisenor.word.presentation.WordScreen
import com.narrowstudio.sisenor.wordList.presentation.WordListViewModel
import org.jetbrains.compose.resources.ExperimentalResourceApi
import org.jetbrains.compose.resources.stringResource
import org.jetbrains.compose.ui.tooling.preview.Preview
import sisenor.composeapp.generated.resources.Res
import sisenor.composeapp.generated.resources.popular_words
import sisenor.composeapp.generated.resources.selected
import sisenor.composeapp.generated.resources.selection_top_bar

class WordListSelectionScreen : Screen {
    @OptIn(
        ExperimentalMaterial3Api::class, ExperimentalResourceApi::class,
        ExperimentalMaterial3WindowSizeClassApi::class
    )
    @Composable
    override fun Content() {
        val navigator = LocalNavigator.current
        val viewModel = koinViewModel<WordListViewModel>()
        val state by viewModel.state.collectAsState()
        val selectedBottomRange by viewModel.selectedBottomRangeProcessed.collectAsState()
        val selectedTopRange by viewModel.selectedTopRangeProcessed.collectAsState()

        val windowSize = calculateWindowSizeClass()
        when (windowSize.widthSizeClass) {
            WindowWidthSizeClass.Compact -> {


                Scaffold(
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
                                modifier = Modifier.fillMaxHeight(.7f),
                                onEvent = { range ->
                                    viewModel.onRangeChanged(range)
                                }
                            )
                            Spacer(modifier = Modifier.size(70.dp))
                            Button(
                                onClick = {
                                    navigator?.push(WordScreen())
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

            else -> Scaffold(
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
                    Row(
                        modifier = Modifier
                            .fillMaxSize(),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.Center
                    ) {
                        RangeSlider(
                            bottomRange = viewModel.bottomRange,
                            topRange = viewModel.topRange,
                            selectedBottomRange = selectedBottomRange,
                            selectedTopRange = selectedTopRange,
                            modifier = Modifier.fillMaxWidth(.7f),
                            onEvent = { range ->
                                viewModel.onRangeChanged(range)
                            }
                        )
                        Spacer(modifier = Modifier.size(50.dp))
                        Button(
                            onClick = {
                                navigator?.push(WordScreen())
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
}


@OptIn(ExperimentalResourceApi::class)
@Preview
@Composable
fun RangeSlider(
    bottomRange: Float,
    topRange: Float,
    selectedBottomRange: Int,
    selectedTopRange: Int,
    modifier: Modifier = Modifier,
    onEvent: (ClosedFloatingPointRange<Float>) -> Unit
) {
    var sliderPosition by remember { mutableStateOf(bottomRange..topRange) }
    Column(
        modifier = modifier.padding(40.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = stringResource(Res.string.selected),
            modifier = Modifier.fillMaxWidth(.8f),
            textAlign = TextAlign.Center,
            fontSize = 24.sp,
            //lineHeight = 44.sp,
        )
        Text(
            text = "$selectedBottomRange - $selectedTopRange",
            modifier = Modifier.fillMaxWidth(.8f),
            textAlign = TextAlign.Center,
            color = MaterialTheme.colorScheme.primary,
            fontSize = 44.sp,
            //lineHeight = 44.sp,
        )
        Text(
            text = stringResource(Res.string.popular_words),
            modifier = Modifier.fillMaxWidth(.8f),
            textAlign = TextAlign.Center,
            fontSize = 24.sp,
            //lineHeight = 44.sp,
        )
        Spacer(modifier = Modifier.size(150.dp))
        RangeSlider(
            value = sliderPosition,
            steps = 11,
            onValueChange = { range ->
                // disabling start and end values to be the same
                if (range.start != range.endInclusive) {
                    sliderPosition = range
                    onEvent(sliderPosition)
                }
            },
            valueRange = 0f..12f,
            onValueChangeFinished = {
                onEvent(sliderPosition)
            },

            )
    }
}
