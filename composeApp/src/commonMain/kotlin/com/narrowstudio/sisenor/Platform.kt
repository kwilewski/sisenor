package com.narrowstudio.sisenor

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform