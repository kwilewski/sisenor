package com.narrowstudio.sisenor.screen.home

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.sp
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.navigator.LocalNavigator
import com.narrowstudio.sisenor.core.presentation.WordListTheme
import com.narrowstudio.sisenor.screen.wordListSelection.WordListSelectionScreen
import org.jetbrains.compose.resources.ExperimentalResourceApi
import org.jetbrains.compose.resources.stringArrayResource
import sisenor.composeapp.generated.resources.Res
import sisenor.composeapp.generated.resources.hello_array
import sisenor.composeapp.generated.resources.start_array
import kotlin.random.Random

class HomeScreen : Screen {
    @Composable
    override fun Content() {
        val navigator = LocalNavigator.current

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
                                    navigator?.push(WordListSelectionScreen())
                                },
                            contentAlignment = Alignment.Center,
                        ) {
                            Text(
                                text = getStartString(),
                                fontSize = 30.sp,
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
    return list[Random.nextInt(0, index)]
}

@OptIn(ExperimentalResourceApi::class)
@Composable
fun getStartString(): String {
    val list = stringArrayResource(Res.string.start_array)
    val index = list.size
    return list[Random.nextInt(0, index)]
}
