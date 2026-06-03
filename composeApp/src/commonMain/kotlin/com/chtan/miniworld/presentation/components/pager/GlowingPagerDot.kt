package com.chtan.miniworld.presentation.components.pager

import androidx.compose.animation.core.EaseInOut
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.unit.dp

@Composable
fun GlowingPagerDot(
    isSelected: Boolean, onClickPagerDot: () -> Unit = {}, modifier: Modifier = Modifier
) {
    val infiniteTransition = rememberInfiniteTransition()
    val glowPulse by infiniteTransition.animateFloat(
        initialValue = 1f, targetValue = 1.22f, animationSpec = infiniteRepeatable(
            animation = tween(1100, easing = EaseInOut), repeatMode = RepeatMode.Reverse
        )
    )

    val dotSize = 5.dp
    val glowColor = MaterialTheme.colorScheme.primary

    Box(
        modifier = modifier.padding(5.dp)                    // Extra space for glow to breathe
            .size(dotSize + 5.dp).graphicsLayer {
                scaleX = if (isSelected) glowPulse else 1f
                scaleY = if (isSelected) glowPulse else 1f
            }.clickable {
                onClickPagerDot()
            }) {
        // Soft outer glow layer
        if (isSelected) {
            Box(
                modifier = Modifier.size(dotSize + 5.dp).align(Alignment.Center).clip(CircleShape)
                    .background(glowColor.copy(alpha = 0.08f)).shadow(
                        elevation = 24.dp,
                        spotColor = glowColor,
                        ambientColor = glowColor,
                        shape = CircleShape
                    )

            )
        }

        // Main glowing dot
        Box(
            modifier = Modifier.size(dotSize).align(Alignment.Center).clip(CircleShape).background(
                glowColor
            ).shadow(
                elevation = if (isSelected) 14.dp else 2.dp,
                spotColor = glowColor,
                ambientColor = glowColor,
                shape = CircleShape
            )
        )
    }
}