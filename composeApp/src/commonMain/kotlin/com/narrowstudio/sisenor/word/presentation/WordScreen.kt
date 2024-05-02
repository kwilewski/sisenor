package com.narrowstudio.sisenor.word.presentation

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.sp
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.navigator.LocalNavigator
import org.jetbrains.compose.resources.stringResource
import sisenor.composeapp.generated.resources.Res
import sisenor.composeapp.generated.resources.selected

class WordScreen : Screen {
    @Composable
    override fun Content() {
        val navigator = LocalNavigator.current
    }

}

@Composable
fun WordDisplayBox(
    spanishWord: String,
    englishWord: String,
    modifier: Modifier = Modifier
){
    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = spanishWord,
            modifier = Modifier.fillMaxWidth(.8f),
            textAlign = TextAlign.Center,
            fontSize = 24.sp,
            color = MaterialTheme.colorScheme.primary,
        )
        Text(
            text = englishWord,
            modifier = Modifier.fillMaxWidth(.8f),
            textAlign = TextAlign.Center,
            fontSize = 24.sp,
            color = MaterialTheme.colorScheme.onBackground,
        )
    }


}
