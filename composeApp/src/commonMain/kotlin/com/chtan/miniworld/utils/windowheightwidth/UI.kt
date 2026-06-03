package com.chtan.miniworld.utils.windowheightwidth

expect fun getWindowHeightWidth(): Pair<Int, Int>
expect fun addResizeListener(onResize: (Pair<Int, Int>) -> Unit)