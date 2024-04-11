package com.narrowstudio.sisenor.core.presentation

import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import com.narrowstudio.sisenor.ui.theme.DarkColors
import com.narrowstudio.sisenor.ui.theme.LightColors
import com.narrowstudio.sisenor.ui.theme.Typography

@Composable
actual fun WordListTheme(
    darkTheme: Boolean,
    dynamicColor: Boolean,
    content: @Composable () -> Unit
) {
    MaterialTheme(
        colorScheme = if(darkTheme) DarkColors else LightColors,
        typography = Typography,
        content = content
    )
}