package com.narrowstudio.sisenor.core.presentation

import android.app.Application
import com.narrowstudio.sisenor.KoinInitializer

class SiSenorApp: Application() {
    override fun onCreate() {
        super.onCreate()
        KoinInitializer(applicationContext).init()
    }
}