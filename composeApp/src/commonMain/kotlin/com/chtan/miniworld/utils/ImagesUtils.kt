package com.chtan.miniworld.utils

import androidx.compose.ui.graphics.ImageBitmap

interface ImagesUtils {
    fun imageConverter(imageBytes: ByteArray): ImageBitmap
}
fun byteArrayToBitmap(imageBytes: ByteArray) : ImageBitmap {
    val converter = getImageConverter()
    return converter.imageConverter(imageBytes)
}