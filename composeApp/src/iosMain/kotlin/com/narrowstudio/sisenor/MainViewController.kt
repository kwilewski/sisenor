package com.narrowstudio.sisenor

import androidx.compose.ui.window.ComposeUIViewController
import com.narrowstudio.sisenor.App

fun MainViewController() = ComposeUIViewController {
    val isDarkTheme = UIScreen.mainScreen.traitCollection.userInterfaceStyle ==
            UIUserInterfaceStyle.UIUserInterfaceStyleDark
    App(
        darkTheme = isDarkTheme,
        dynamicColor = false
    )
}