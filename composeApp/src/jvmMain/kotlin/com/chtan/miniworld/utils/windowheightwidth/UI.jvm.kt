package com.chtan.miniworld.utils.windowheightwidth

import java.awt.Window
import java.awt.event.ComponentAdapter
import java.awt.event.ComponentEvent

actual fun getWindowHeightWidth(): Pair<Int, Int> {
    val window = Window.getWindows().firstOrNull()
    return if (window != null) {
        window.width to window.height
    } else {
        Pair(1024, 768) // Default fallback
    }
}

actual fun addResizeListener(onResize: (Pair<Int, Int>) -> Unit) {
    Window.getWindows().firstOrNull()?.addComponentListener(object : ComponentAdapter() {
        override fun componentResized(e: ComponentEvent?) {
            val window = e?.component as? Window
            if (window != null) {
                onResize(Pair(window.width, window.height))
            }
        }
    })
}
