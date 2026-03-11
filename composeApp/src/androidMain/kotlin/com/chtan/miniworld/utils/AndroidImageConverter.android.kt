package com.chtan.miniworld.utils

import android.graphics.BitmapFactory
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.graphics.asImageBitmap

class AndroidImageConverter : ImagesUtils {
    override fun imageConverter(imageBytes: ByteArray): ImageBitmap {
        return BitmapFactory.decodeByteArray(imageBytes,0, imageBytes.size).asImageBitmap()
    }

}