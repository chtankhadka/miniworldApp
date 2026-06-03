package com.chtan.miniworld.presentation.components.shapes

import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.geometry.RoundRect
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Matrix
import androidx.compose.ui.graphics.Outline
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.unit.Density
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.LayoutDirection

class RoundedParallelogram(
    private val radius: Dp,
    private val skew: Float = -0.1f
) : Shape {

    override fun createOutline(
        size: Size,
        layoutDirection: LayoutDirection,
        density: Density
    ): Outline {

        val path = Path().apply {

            addRoundRect(
                RoundRect(
                    left = 0f,
                    top = 0f,
                    right = size.width,
                    bottom = size.height,
                    topLeftCornerRadius = CornerRadius(radius.value),
                    topRightCornerRadius = CornerRadius(radius.value),
                    bottomLeftCornerRadius = CornerRadius(radius.value),
                    bottomRightCornerRadius = CornerRadius(radius.value)
                )
            )
            // skew effect
            transform(
                androidx.compose.ui.graphics.Matrix().apply {
                    values[Matrix.SkewX] = skew
                }
            )

        }
        path.close()

        return Outline.Generic(path)
    }
}