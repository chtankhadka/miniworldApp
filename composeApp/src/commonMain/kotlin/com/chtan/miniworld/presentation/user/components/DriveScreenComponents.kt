package com.chtan.miniworld.presentation.user.components

import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.spring
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.awaitFirstDown
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDownward
import androidx.compose.material.icons.filled.ArrowUpward
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.contentColorFor
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import com.chtan.miniworld.presentation.user.drive.Components.DrivingMode
import miniworld.composeapp.generated.resources.Res
import miniworld.composeapp.generated.resources.brake
import miniworld.composeapp.generated.resources.clutch
import miniworld.composeapp.generated.resources.steering
import org.jetbrains.compose.resources.painterResource
import kotlin.math.roundToInt


@Composable
fun Break(
    modifier: Modifier = Modifier, onClickBreak: () -> Unit
) {
    var isBreakApplied by remember { mutableStateOf(false) }
    val density = LocalDensity.current.density

    Image(
        modifier = Modifier.size(48.dp).pointerInput(Unit) {
            awaitPointerEventScope {
                while (true) {
                    val event = awaitPointerEvent()
                    val change = event.changes[0]
                    if (change.pressed) {
                        isBreakApplied = true
                        change.consume()
                    } else {
                        isBreakApplied = false
                        change.consume()
                    }
                }
            }
        }.graphicsLayer {
            if (isBreakApplied) {
                rotationX = -35f
                cameraDistance = 18 * density
                onClickBreak()

            }


        }, painter = painterResource(Res.drawable.brake), contentDescription = null
    )
}


@Composable
fun GearSelectorRow(
    value: String,
    onValueChange: (String) -> Unit,
    modifier: Modifier = Modifier,
    trackWidth: Dp,
    highlightColor: Color = Color(0xFF00BFA6),
    inactiveText: Color = Color.Gray,
    activeText: Color = Color.White
) {
    val modes = listOf(DrivingMode.PARK, DrivingMode.BACK, DrivingMode.DRIVE)
    val currentMode = modes.find { it.short == value } ?: modes.first()
    val density = LocalDensity.current

    BoxWithConstraints(
        modifier = modifier.width(trackWidth)
    ) {
        // Auto-calc highlight width based on full width
        val highlightWidth = maxWidth / modes.size

        // Pre-calc px
        val highlightPx = with(density) { highlightWidth.toPx() }
        val trackPx = with(density) { maxWidth.toPx() }

        // Step between positions
        val stepPx = (trackPx - highlightPx) / (modes.size - 1)

        val offsetX = remember { Animatable(0f) }

        // Animate sliding background
        LaunchedEffect(value) {
            val index = modes.indexOf(currentMode)
            offsetX.animateTo(
                targetValue = index * stepPx,
                animationSpec = spring(
                    dampingRatio = Spring.DampingRatioNoBouncy,
                    stiffness = Spring.StiffnessMediumLow
                )
            )
        }

        // 🟩 Sliding Highlight Behind Text
        Box(
            modifier = Modifier
                .offset { IntOffset(offsetX.value.toInt(), 0) }
                .width(highlightWidth)
                .height(highlightWidth)
                .clip(RoundedCornerShape(16.dp))
                .background(highlightColor)
        )

        // 🔤 Only One Row of Letters
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 8.dp)
                .height(IntrinsicSize.Min),  // Auto height
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            modes.forEach { mode ->
                val isActive = mode == currentMode

                Text(
                    text = mode.short,
                    color = if (isActive) activeText else inactiveText,
                    style = MaterialTheme.typography.titleLarge.copy(fontWeight = FontWeight.Bold),
                    modifier = Modifier
                        .padding(vertical = 8.dp)
                        .clickable { onValueChange(mode.short) }
                )
            }
        }
    }
}


@Composable
fun VerticalAcceleratorPedal(
    modifier: Modifier = Modifier,
    trackHeight: Dp = 150.dp,
    iconSize: Dp = 80.dp,
    minValue: Int = 0,
    maxValue: Int = 9,
    lineColor: Color = Color.DarkGray,
    iconColor: Color = Color.Blue,
    density: Float = 1f,
    onValueChange: (Int) -> Unit = {}
) {
    var dragOffset by remember { mutableStateOf(0f) } // px from center
    var isDragging by remember { mutableStateOf(false) }

    val trackHeightPx = trackHeight.value * density
    val iconSizePx = iconSize.value * density
    val halfTrackPx = trackHeightPx / 2f
    val maxOffset = halfTrackPx - (iconSizePx / 2) // Ensure center stays within track

    // Initial position at bottom (max offset = higher value)
    val initialOffset = maxOffset
    LaunchedEffect(Unit) {
        dragOffset = initialOffset
        onValueChange(minValue)
    }

    val animatedOffset by animateFloatAsState(
        targetValue = if (isDragging) dragOffset else initialOffset,
        animationSpec = spring(dampingRatio = 0.6f, stiffness = 300f),
        label = "pedalSpringBack"
    )

    // Current speed value derived from the displayed (animated) offset
    val currentSpeed = remember {
        derivedStateOf {
            val offset = animatedOffset.coerceIn(-maxOffset, maxOffset)
            val positionRatio = 1f - ((offset / maxOffset + 1f) / 2f) // bottom -> 1, top -> 0
            val valueRange = maxValue - minValue
            (minValue + (positionRatio * valueRange)).roundToInt().coerceIn(minValue, maxValue)
        }
    }

    // Call onValueChange whenever the displayed speed changes
    LaunchedEffect(currentSpeed.value) {
        onValueChange(currentSpeed.value)
    }

    val coroutineScope = rememberCoroutineScope()

    Box(
        modifier = modifier
            .width(iconSize + 20.dp)
            .height(trackHeight)
            .pointerInput(Unit) {
                awaitPointerEventScope {
                    while (true) {
                        val down = awaitFirstDown(requireUnconsumed = false)
                        isDragging = true
                        val centerY = size.height / 2f
                        var currentOffset = (down.position.y - centerY).coerceIn(-maxOffset, maxOffset)
                        dragOffset = currentOffset
                        down.consume()

                        while (true) {
                            val event = awaitPointerEvent()
                            val change = event.changes[0]
                            if (change.pressed) {
                                val currentY = change.position.y
                                currentOffset = (currentY - centerY).coerceIn(-maxOffset, maxOffset)
                                dragOffset = currentOffset
                                change.consume()
                            } else {
                                // Release
                                isDragging = false
                                change.consume()
                                break
                            }
                        }
                    }
                }
            }
    ) {
        // Vertical line track (uncomment if you want it visible)
//        Canvas(modifier = Modifier.fillMaxSize()) {
//            val centerX = size.width / 2
//            drawLine(
//                color = lineColor,
//                start = Offset(centerX, 0f),
//                end = Offset(centerX, size.height),
//                strokeWidth = 4f * density
//            )
//        }

        val displayOffsetPx = animatedOffset.coerceIn(-maxOffset, maxOffset)
        val offsetDpY = (displayOffsetPx / density).dp

        Image(
            modifier = Modifier
                .size(iconSize)
                .align(Alignment.Center)
                .offset(y = offsetDpY)
                .graphicsLayer {
                    rotationX = (-(currentSpeed.value - 3) * 7f)
                    cameraDistance = 18 * density
                },
            painter = painterResource(Res.drawable.clutch),
            contentDescription = null
        )
    }
}


@Composable
fun HorizontalAutoCenterSlider(
    modifier: Modifier = Modifier,
    trackWidth: Dp = 200.dp,
    iconSize: Dp = 80.dp,
    minValue: Int = -5,
    maxValue: Int = 5,
    centerValue: Int = 0,
    lineColor: Color = Color.DarkGray,
    density: Float = 1f,
    onValueChange: (Int) -> Unit = {}
) {
    var dragOffset by remember { mutableStateOf(0f) }
    var isDragging by remember { mutableStateOf(false) }

    val trackWidthPx = trackWidth.value * density
    val iconSizePx = iconSize.value * density
    val halfTrackPx = trackWidthPx / 2f
    val maxOffset = halfTrackPx - (iconSizePx / 2f)

    // Animate back to center (0f) when not dragging
    val animatedOffset by animateFloatAsState(
        targetValue = if (isDragging) dragOffset else 0f,
        animationSpec = spring(dampingRatio = 0.6f, stiffness = 300f),
        label = "autoCenterSpring"
    )

    // Current value derived from the *visible* (animated) position
    val currentValue = remember {
        derivedStateOf {
            val offset = animatedOffset.coerceIn(-maxOffset, maxOffset)
            val positionRatio = (offset / maxOffset + 1f) / 2f
            val valueRange = maxValue - minValue
            (minValue + (positionRatio * valueRange)).roundToInt().coerceIn(minValue, maxValue)
        }
    }

    // Call onValueChange whenever the displayed value changes (during drag AND return)
    LaunchedEffect(currentValue.value) {
        onValueChange(currentValue.value)
    }

    // Initial center position
    LaunchedEffect(Unit) {
        onValueChange(centerValue)
    }

    Box(
        modifier = modifier
            .width(trackWidth)
            .height(iconSize + 20.dp)
            .pointerInput(Unit) {
                awaitPointerEventScope {
                    while (true) {
                        val down = awaitFirstDown(requireUnconsumed = false)
                        isDragging = true
                        val centerX = size.width / 2f
                        var currentOffset = (down.position.x - centerX).coerceIn(-maxOffset, maxOffset)
                        dragOffset = currentOffset
                        down.consume()

                        while (true) {
                            val event = awaitPointerEvent()
                            val change = event.changes.first()
                            if (change.pressed) {
                                val currentX = change.position.x
                                currentOffset = (currentX - centerX).coerceIn(-maxOffset, maxOffset)
                                dragOffset = currentOffset
                                change.consume()
                            } else {
                                // Finger released
                                isDragging = false
                                change.consume()
                                break
                            }
                        }
                    }
                }
            }
    ) {
        // Optional track line
//        Canvas(modifier = Modifier.fillMaxSize()) {
//            val centerY = size.height / 2f
//            drawLine(
//                color = lineColor,
//                start = Offset(0f, centerY),
//                end = Offset(size.width, centerY),
//                strokeWidth = 4f * density
//            )
//        }

        val displayOffsetPx = animatedOffset.coerceIn(-maxOffset, maxOffset)
        val offsetDpX = (displayOffsetPx / density).dp

        Image(
            modifier = Modifier
                .size(iconSize)
                .align(Alignment.Center)
                .offset(x = offsetDpX)
                .rotate(offsetDpX.value), // Keeps your rotation effect
            painter = painterResource(Res.drawable.steering),
            contentDescription = "Steering Wheel"
        )
    }
}


@Composable
fun GearChange(
    onClickGear: (Int) -> Unit, modifier: Modifier = Modifier, gear: Int
) {
    var gear by remember { mutableStateOf(gear) }
    Box(modifier = modifier.padding(5.dp)) {
        Row(
            horizontalArrangement = Arrangement.spacedBy(10.dp)
        ) {
            IconButton(onClick = {
                if (gear < 4) {
                    gear++
                    onClickGear(gear)
                }
            }, content = {
                Icon(imageVector = Icons.Default.ArrowUpward, contentDescription = null, tint = contentColorFor(backgroundColor = MaterialTheme.colorScheme.primary ))
            })
            Text(text = "G$gear", color = contentColorFor(backgroundColor = MaterialTheme.colorScheme.primary ))
            IconButton(onClick = {
                if (gear > 0) {
                    gear--
                    onClickGear(gear)
                }
            }, content = {
                Icon(imageVector = Icons.Default.ArrowDownward, contentDescription = null,tint = contentColorFor(backgroundColor = MaterialTheme.colorScheme.primary ))
            })
        }

    }
}