package com.chtan.miniworld.utils.windowheightwidth

import android.content.res.Resources

actual fun getWindowHeightWidth(): Pair<Int, Int> {
    val displayMetrics = Resources.getSystem().displayMetrics
    return Pair(displayMetrics.widthPixels, displayMetrics.heightPixels)
}

actual fun addResizeListener(onResize: (Pair<Int, Int>) -> Unit) {
    // On Android, window resizing is usually handled by the system 
    // triggering a configuration change (re-creating the Activity or calling onConfigurationChanged).
    // For manual tracking, you can poll or react to configuration changes in the Activity.
    // For now, we return the current size.
    onResize(getWindowHeightWidth())
}
