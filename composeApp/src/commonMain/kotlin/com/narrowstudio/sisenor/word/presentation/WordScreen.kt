package com.narrowstudio.sisenor.word.presentation

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
import androidx.compose.material.icons.filled.Pause
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material.icons.filled.RoundedCorner
import androidx.compose.material.icons.filled.SkipNext
import androidx.compose.material.icons.filled.SkipPrevious
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.ViewModel
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.navigator.LocalNavigator
import com.narrowstudio.sisenor.koinViewModel
import com.narrowstudio.sisenor.screen.TopBar
import com.narrowstudio.sisenor.word.domain.Word
import dev.icerock.moko.mvvm.compose.getViewModel
import org.jetbrains.compose.resources.ExperimentalResourceApi
import org.jetbrains.compose.resources.stringResource
import org.jetbrains.compose.ui.tooling.preview.Preview
import sisenor.composeapp.generated.resources.Res
import sisenor.composeapp.generated.resources.app_name
import sisenor.composeapp.generated.resources.selected
import sisenor.composeapp.generated.resources.selection_top_bar

class WordScreen : Screen {
    @OptIn(ExperimentalResourceApi::class)
    @Composable
    override fun Content() {
        val navigator = LocalNavigator.current
        
        val viewModel = koinViewModel<WordViewModel>()

        val state by viewModel.state.collectAsState()

        val wordState = viewModel.currentWordState

        val wordFromWM = viewModel.stateFromWM.collectAsState(
            Word(
                id = 0,
                spanishWord = " gafd",
                englishWord = " adfgh",
                isLearned = false,
                isSimilar = false,
                audioPath = null
            )
        )
        val timerState = viewModel.timerState.collectAsState(false)

        Scaffold(
            topBar = {
                TopBar(
                    title = stringResource(Res.string.app_name),
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
                    WordDisplayBox(
                        word = wordFromWM.value,
                        spanishWord = "spanish",
                        englishWord = "english",
                        modifier = Modifier.fillMaxWidth()
                            .fillMaxHeight(0.6f)
                            .padding(16.dp)
                    )
                    Spacer(
                        modifier = Modifier
                            .height(20.dp)
                    )
                    ControlButtons(
                        modifier = Modifier
                            .fillMaxWidth()
                            .fillMaxHeight(.5f)
                            .padding(16.dp),
                        isRunningState = timerState.value,
                        onEvent = viewModel::onEvent
                    )

                }
            }
        }
    }

}

@Preview
@Composable
fun WordDisplayBox(
    word: Word,
    spanishWord: String,
    englishWord: String,
    modifier: Modifier = Modifier
) {
    Card(modifier = modifier) {
        Column(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
                    Text(
                        text = word.spanishWord?: spanishWord,
                        modifier = Modifier.fillMaxWidth(.8f),
                        textAlign = TextAlign.Center,
                        fontSize = 40.sp,
                        lineHeight = 40.sp,
                        color = MaterialTheme.colorScheme.primary,
                    )
            Spacer(
                modifier = Modifier.size(20.dp)
            )
                Text(
                    text = word.englishWord?: englishWord,
                    modifier = Modifier.fillMaxWidth(.8f),
                    textAlign = TextAlign.Center,
                    fontSize = 40.sp,
                    lineHeight = 40.sp,
                    color = MaterialTheme.colorScheme.onPrimaryContainer,
                )
        }
    }
}

@Preview
@Composable
fun ControlButtons(
    modifier: Modifier = Modifier,
    isRunningState: Boolean,
    onEvent: (WordEvent) -> Unit
) {
    Row(
        modifier = modifier,
        horizontalArrangement = Arrangement.SpaceAround,
        verticalAlignment = Alignment.CenterVertically
    ) {
        // previous button
        Button(
            onClick = {
                onEvent(WordEvent.onPreviousClick())
            },
            modifier = Modifier.height(70.dp)
                .width(70.dp),
            shape = RoundedCornerShape(50),
        ) {
            Icon(
                Icons.Default.SkipPrevious,
                contentDescription = "Previous",
                tint = MaterialTheme.colorScheme.onPrimary,
                modifier = Modifier.size(50.dp)
            )
        }

        // play button
        Button(
            onClick = {
                onEvent(WordEvent.onStartClick())
            },
            modifier = Modifier.height(100.dp)
                .width(100.dp),
            shape = RoundedCornerShape(50),
        ) {
            if (isRunningState) {
                Icon(
                    Icons.Default.Pause,
                    contentDescription = "Paused",
                    tint = MaterialTheme.colorScheme.onPrimary,
                    modifier = Modifier.size(70.dp)
                )
            } else {
                Icon(
                    Icons.Default.PlayArrow,
                    contentDescription = "Play",
                    tint = MaterialTheme.colorScheme.onPrimary,
                    modifier = Modifier.size(70.dp)
                )
            }
        }

        // next button
        Button(
            onClick = {
                onEvent(WordEvent.onNextClick())
            },
            modifier = Modifier.height(70.dp)
                .width(70.dp),
            shape = RoundedCornerShape(50),
        ) {
            Icon(
                Icons.Default.SkipNext,
                contentDescription = "Next",
                tint = MaterialTheme.colorScheme.onPrimary,
                modifier = Modifier.size(50.dp)
            )
        }
    }
}
