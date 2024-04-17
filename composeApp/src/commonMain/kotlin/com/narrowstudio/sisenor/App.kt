package com.narrowstudio.sisenor

import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.narrowstudio.sisenor.core.presentation.WordListTheme
import com.narrowstudio.sisenor.wordList.presentation.WordListScreen
import com.narrowstudio.sisenor.wordList.presentation.WordListViewModel
import dev.icerock.moko.mvvm.compose.getViewModel
import dev.icerock.moko.mvvm.compose.viewModelFactory
import org.jetbrains.compose.ui.tooling.preview.Preview


@Composable
@Preview
fun App(
    darkTheme: Boolean,
    dynamicColor: Boolean
) {/*
    WordListTheme(
        darkTheme = darkTheme,
        dynamicColor = dynamicColor
    ) {
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
    }*/
    WordListTheme(
        darkTheme = darkTheme,
        dynamicColor = dynamicColor
    ) {
        Surface(
            color = MaterialTheme.colorScheme.background
        ) {
            Column(
                modifier = Modifier
                    .fillMaxSize(),
                horizontalAlignment = Alignment.CenterHorizontally,
//                verticalArrangement = Arrangement.SpaceAround
            ) {
                //------------------------------------------------------------ Greeting screen
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .weight(1f)
                ) {
                    Text(
                        modifier = Modifier.align(Alignment.Center),
                        text = "Buenos dias",
                        fontSize = 50.sp,
                        fontWeight = FontWeight.Bold
                    )
                }
                // ---------------------------------------------------------- Start button
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .weight(1f)
                ) {
                    Column(
                        modifier = Modifier.fillMaxSize(),
                        horizontalAlignment = Alignment.CenterHorizontally,
                    ) {
                        Box(
                            modifier = Modifier.weight(3f)
                                .clickable {
                                    //TODO
                                },
                            contentAlignment = Alignment.Center,
                        ) {
                            Text(
                                text = "Vamos",
                                fontSize = 50.sp,
                                fontWeight = FontWeight.Bold,
                                modifier = Modifier.fillMaxWidth(),
                                textAlign = TextAlign.Center
                            )
                        }
                        Box(
                            modifier = Modifier.weight(2f)
                        )
                    }
                }
            }
        }

    }
}
