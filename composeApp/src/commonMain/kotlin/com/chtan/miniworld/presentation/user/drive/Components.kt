package com.chtan.miniworld.presentation.user.drive
import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.spring
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.hapticfeedback.HapticFeedbackType
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.platform.LocalHapticFeedback
import androidx.compose.ui.semantics.*
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
object Components {

    enum class DrivingMode(val short: String, val label: String) {
        PARK("P", "Parking"),
        BACK("B", "Back"),
        DRIVE("D", "Drive")
    }

    @Composable
    fun GearSelectorRow(
        value: DrivingMode,
        onValueChange: (DrivingMode) -> Unit,
        modifier: Modifier = Modifier,
        trackWidth: Dp = 200.dp,
        trackHeight: Dp = 70.dp,
        knobSize: Dp = 50.dp,
        activeColor: Color = Color(0xFF00BFA6),
        inactiveText: Color = Color.Gray,
        activeText: Color = Color.White
    ) {
        val modes = remember { listOf(DrivingMode.PARK, DrivingMode.BACK, DrivingMode.DRIVE) }
        val haptics = LocalHapticFeedback.current
        val density = LocalDensity.current

        val knobPx = with(density) { knobSize.toPx() }
        val trackPx = with(density) { trackWidth.toPx() }
        val gapPx = remember(trackPx, knobPx, modes.size) {
            (trackPx - knobPx) / (modes.size - 1)
        }

        // Horizontal animation
        val offsetX = remember { Animatable(0f) }

        // Animate to position
        LaunchedEffect(value) {
            val targetIndex = modes.indexOf(value).coerceIn(0, modes.lastIndex)
            val targetOffset = targetIndex * gapPx
            offsetX.animateTo(
                targetValue = targetOffset,
                animationSpec = spring(
                    dampingRatio = Spring.DampingRatioNoBouncy,
                    stiffness = Spring.StiffnessMediumLow
                )
            )
        }

        Column(
            modifier = modifier
                .width(trackWidth)
                .height(trackHeight)
                .clip(RoundedCornerShape(40.dp)),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            // Labels on top of track
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = (knobSize / 2)),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                modes.forEach { mode ->
                    val isActive = mode == value
                    Text(
                        text = mode.short,
                        color = if (isActive) activeColor else inactiveText,
                        style = MaterialTheme.typography.titleMedium.copy(
                            fontWeight = if (isActive) FontWeight.Bold else FontWeight.Normal
                        ),
                        modifier = Modifier
                            .clickable(
                                indication = null,
                                interactionSource = remember { MutableInteractionSource() }
                            ) {
                                if (mode != value) {
                                    onValueChange(mode)
                                    haptics.performHapticFeedback(HapticFeedbackType.TextHandleMove)
                                }
                            }
                    )
                }
            }

            // Track with knob
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(knobSize)
                    .clip(RoundedCornerShape(40.dp)),
                contentAlignment = Alignment.CenterStart
            ) {
                // Movable knob
                Box(
                    modifier = Modifier
                        .offset(x = with(density) { offsetX.value.toDp() })
                        .size(knobSize)
                        .clip(CircleShape)
                        .background(activeColor),
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = value.short,
                        color = activeText,
                        style = MaterialTheme.typography.titleMedium.copy(fontWeight = FontWeight.Black)
                    )
                }
            }
        }
    }

    /*
    @Preview(showBackground = true)
    @Composable
    fun PreviewGearSelector() {
        Surface { GearSelectorPreview() }
    }
    */


    /**
     * Optional compact pill-style segmented toggle (horizontal).
     */
    @Composable
    fun SegmentedGearToggle(
        value: DrivingMode,
        onValueChange: (DrivingMode) -> Unit,
        modifier: Modifier = Modifier,
        height: Dp = 48.dp
    ) {
        val modes = listOf(DrivingMode.PARK, DrivingMode.BACK, DrivingMode.DRIVE)
        val bg = MaterialTheme.colorScheme.surfaceVariant
        val fg = MaterialTheme.colorScheme.onSurfaceVariant
        val active = MaterialTheme.colorScheme.primary
        val onActive = MaterialTheme.colorScheme.onPrimary

        Row(
            modifier
                .clip(RoundedCornerShape(999.dp))
                .background(bg)
                .height(height)
                .padding(4.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            modes.forEach { mode ->
                val selected = mode == value
                Box(
                    modifier = Modifier
                        .weight(1f)
                        .fillMaxHeight()
                        .clip(RoundedCornerShape(999.dp))
                        .background(if (selected) active else Color.Transparent)
                        .clickable { onValueChange(mode) }
                        .padding(horizontal = 12.dp),
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = mode.short,
                        color = if (selected) onActive else fg,
                        style = MaterialTheme.typography.titleMedium,
                        modifier = Modifier.semantics {
                            role = Role.Switch
                            stateDescription = "Gear ${mode.label}"
//                            if (selected) selected()
                        }
                    )
                }
            }
        }
    }
}