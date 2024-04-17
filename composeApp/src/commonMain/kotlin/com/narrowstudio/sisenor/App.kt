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
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.launch
import org.jetbrains.compose.resources.ExperimentalResourceApi
import org.jetbrains.compose.resources.StringResource
import org.jetbrains.compose.resources.getString
import org.jetbrains.compose.resources.stringArrayResource
import org.jetbrains.compose.resources.stringResource
import org.jetbrains.compose.ui.tooling.preview.Preview
import sisenor.composeapp.generated.resources.Res
import sisenor.composeapp.generated.resources.hello_array
import sisenor.composeapp.generated.resources.welcome
import kotlin.random.Random


@OptIn(ExperimentalResourceApi::class)
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
    ){
        val scope = rememberCoroutineScope()

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
                        text = getHelloString(),
                        fontSize = 50.sp,
                        fontWeight = FontWeight.Bold,
                        textAlign = TextAlign.Center
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

@OptIn(ExperimentalResourceApi::class)
@Composable
fun getHelloString(): String {
    val list = stringArrayResource(Res.string.hello_array)
    val index = list.size
    return list[Random.nextInt(0, index - 1)]
}
