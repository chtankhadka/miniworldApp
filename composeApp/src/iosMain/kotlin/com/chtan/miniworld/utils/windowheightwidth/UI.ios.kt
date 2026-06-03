package com.chtan.miniworld.utils.windowheightwidth

import kotlinx.cinterop.ExperimentalForeignApi
import kotlinx.cinterop.useContents
import platform.UIKit.UIScreen

@OptIn(ExperimentalForeignApi::class)
actual fun getWindowHeightWidth(): Pair<Int, Int> {
    val mainScreen = UIScreen.mainScreen
    val bounds = mainScreen.bounds
    return bounds.useContents {
        size.width.toInt() to size.height.toInt()
    }
}

actual fun addResizeListener(onResize: (Pair<Int, Int>) -> Unit) {
    // On iOS, resize events are typically handled via orientation changes or view layout updates.
    // For a simple implementation, we can leave this as a no-op or implement it using notifications if needed.
}
