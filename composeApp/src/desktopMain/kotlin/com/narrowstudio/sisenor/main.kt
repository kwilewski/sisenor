package com.narrowstudio.sisenor

import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application
import com.narrowstudio.sisenor.App

fun main() = application {
    Window(onCloseRequest = ::exitApplication, title = "Si senor") {
        App()
    }
}