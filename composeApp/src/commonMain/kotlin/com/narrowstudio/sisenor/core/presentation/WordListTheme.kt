package com.narrowstudio.sisenor.core.presentation

import androidx.compose.runtime.Composable

@Composable
expect fun WordListTheme(
    darkTheme: Boolean,
    dynamicColor: Boolean,
    content: @Composable () -> Unit
)