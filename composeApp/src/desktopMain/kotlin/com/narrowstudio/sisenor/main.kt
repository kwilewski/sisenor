package com.narrowstudio.sisenor

import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application
import com.narrowstudio.sisenor.di.AppModule
import com.narrowstudio.sisenor.di.dataSourceModule
import org.koin.core.KoinApplication
import org.koin.core.context.startKoin


fun main() = application {
    startKoin {
        modules(viewModelModule, dataSourceModule)
    }


    Window(onCloseRequest = ::exitApplication, title = "Si senor") {
            App(
                darkTheme = false,
                dynamicColor = false,
                appModule = AppModule()
            )
    }
}
