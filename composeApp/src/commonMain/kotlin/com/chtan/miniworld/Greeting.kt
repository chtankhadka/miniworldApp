package com.chtan.miniworld

class Greeting {
    private val platform = getPlatform()

    fun greet(): String {
        return "chetan, ${platform.name}!"
    }
}