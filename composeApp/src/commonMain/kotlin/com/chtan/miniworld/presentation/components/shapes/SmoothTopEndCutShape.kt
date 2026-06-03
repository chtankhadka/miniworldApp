package com.chtan.miniworld.presentation.components.shapes

import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Outline
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.unit.Density
import androidx.compose.ui.unit.LayoutDirection

class SmoothTopEndCutShape(
    private val cutWidthPercent: Float = 0.18f,
    private val cutHeightPercent: Float = 0.35f
) : Shape {

    override fun createOutline(
        size: Size,
        layoutDirection: LayoutDirection,
        density: Density
    ): Outline {

        val cutWidth = size.width * cutWidthPercent
        val cutHeight = size.height * cutHeightPercent

        return Outline.Generic(
            Path().apply {
                moveTo(0f, 0f)

                lineTo(size.width - cutWidth, 0f)

                quadraticTo(
                    size.width, 0f,
                    size.width, cutHeight
                )

                lineTo(size.width, size.height)
                lineTo(0f, size.height)
                close()
            }
        )
    }
}