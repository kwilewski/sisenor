package com.narrowstudio.sisenor

import androidx.compose.ui.window.ComposeUIViewController
import com.narrowstudio.sisenor.App
import com.narrowstudio.sisenor.di.AppModule

fun MainViewController() = ComposeUIViewController(
    configure = {
        KoinInitializer().init()
    }
) {
    val isDarkTheme = UIScreen.mainScreen.traitCollection.userInterfaceStyle ==
            UIUserInterfaceStyle.UIUserInterfaceStyleDark
    App(
        darkTheme = isDarkTheme,
        dynamicColor = false,
        appModule = AppModule()
    )
}