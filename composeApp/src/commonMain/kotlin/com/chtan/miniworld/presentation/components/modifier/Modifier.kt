package com.chtan.miniworld.presentation.components.modifier

import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

internal fun Modifier.bottomBorder(
    width: Dp = 1.dp,
    color: List<Color> = listOf(Color.White, Color.Transparent)
) = drawBehind {
    drawLine(
        brush = Brush.horizontalGradient(
            color
        ),
        start = Offset(0f, size.height),
        end = Offset(size.width, size.height),
        strokeWidth = width.toPx()
    )
}