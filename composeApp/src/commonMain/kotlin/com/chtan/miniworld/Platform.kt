package com.chtan.miniworld

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform