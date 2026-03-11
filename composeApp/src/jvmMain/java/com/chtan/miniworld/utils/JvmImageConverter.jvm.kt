package com.chtan.miniworld.utils

import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.graphics.toComposeImageBitmap
import org.jetbrains.skia.Image
import java.io.ByteArrayInputStream
import javax.imageio.ImageIO
import org.jetbrains.skia.Bitmap
import org.jetbrains.skia.ColorAlphaType
import org.jetbrains.skia.ColorType
import org.jetbrains.skia.ImageInfo

class JvmImageConverter : ImagesUtils {
    override fun imageConverter(imageBytes: ByteArray): ImageBitmap {
        return try {
            val skiaImage = Image.makeFromEncoded(imageBytes)
            skiaImage.toComposeImageBitmap()
        } catch (e: Exception) {
            e.printStackTrace()
            ImageBitmap(1, 1)
        }
    }
}
