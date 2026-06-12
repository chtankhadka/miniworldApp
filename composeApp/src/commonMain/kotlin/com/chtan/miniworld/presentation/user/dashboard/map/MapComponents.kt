package com.chtan.miniworld.presentation.user.dashboard.map

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.Image
import androidx.compose.foundation.gestures.detectDragGestures
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import miniworld.composeapp.generated.resources.Res
import miniworld.composeapp.generated.resources.testCountryMap
import org.jetbrains.compose.resources.painterResource
import kotlin.collections.plus
import kotlin.math.sqrt

data class Land(
    val id: String,
    val points: List<MapPoint>
)


data class MapPoint(
    val x: Float,
    val y: Float
)

@Composable
fun UserMapScreenPointsGenerator() {
    var canvasWidth by remember { mutableStateOf(1f) }
    var canvasHeight by remember { mutableStateOf(1f) }

    var polygonClosed by remember {
        mutableStateOf(false)
    }

    // SAVE THESE TO DATABASE
    var normalizedPoints by remember {
        mutableStateOf<List<MapPoint>>(
            emptyList())
    }

    Box(
        modifier = Modifier.fillMaxSize()
    ) {

        Box(
            modifier = Modifier
                .fillMaxHeight()
                .aspectRatio(1f)
                .align(Alignment.Center)
        ) {

            Image(
                painter = painterResource(Res.drawable.testCountryMap),
                contentDescription = null,
                contentScale = ContentScale.FillBounds,
                modifier = Modifier.fillMaxSize()
            )

            Canvas(
                modifier = Modifier
                    .fillMaxSize()
                    .pointerInput(polygonClosed) {

                        detectDragGestures(

                            onDragStart = { offset ->

                                if (polygonClosed) return@detectDragGestures

                                normalizedPoints =
                                    normalizedPoints + offset.toNormalized(
                                        canvasWidth,
                                        canvasHeight
                                    )
                            },

                            onDrag = { change, _ ->

                                if (polygonClosed) return@detectDragGestures

                                val screenPoints =
                                    normalizedPoints.map {
                                        it.toOffset(
                                            canvasWidth,
                                            canvasHeight
                                        )
                                    }

                                val last = screenPoints.lastOrNull()

                                if (shouldAddPoint(last, change.position)) {

                                    normalizedPoints =
                                        normalizedPoints + change.position.toNormalized(
                                            canvasWidth,
                                            canvasHeight
                                        )
                                }
                            }
                        )
                    }
            ) {

                canvasWidth = size.width
                canvasHeight = size.height

                val screenPoints =
                    normalizedPoints.map {
                        it.toOffset(
                            size.width,
                            size.height
                        )
                    }

                if (screenPoints.size > 1) {

                    val path = Path().apply {

                        moveTo(
                            screenPoints.first().x,
                            screenPoints.first().y
                        )

                        screenPoints.drop(1).forEach {
                            lineTo(it.x, it.y)
                        }

                        if (polygonClosed) {
                            close()
                        }
                    }

                    drawPath(
                        path = path,
                        color = Color.Red,
                        style = Stroke(width = 4f)
                    )
                }

                screenPoints.forEach {

                    drawCircle(
                        color = Color.Blue,
                        radius = 8f,
                        center = it
                    )
                }
            }

            Column(
                modifier = Modifier
                    .align(Alignment.TopEnd)
                    .padding(16.dp)
            ) {

                Button(
                    onClick = {
                        polygonClosed = true
                    }
                ) {
                    Text("Close")
                }

                Spacer(
                    modifier = Modifier.height(8.dp)
                )

                Button(
                    onClick = {

                        normalizedPoints = emptyList()
                        polygonClosed = false
                    }
                ) {
                    Text("Clear")
                }

                Spacer(
                    modifier = Modifier.height(8.dp)
                )

                Button(
                    onClick = {

                        println()
                        println("===== EXPORT =====")

                        normalizedPoints.forEach {

                            println(
                                "MapPoint(${it.x}f, ${it.y}f),"
                            )
                        }

                        println("===== END =====")
                        println()
                    }
                ) {
                    Text("Export")
                }
            }
        }
    }
}

fun Offset.toNormalized(
    width: Float,
    height: Float
): MapPoint {

    return MapPoint(
        x = x / width,
        y = y / height
    )
}

fun MapPoint.toOffset(
    width: Float,
    height: Float
): Offset {

    return Offset(
        x = x * width,
        y = y * height
    )
}

fun shouldAddPoint(
    last: Offset?,
    current: Offset
): Boolean {

    if (last == null) return true

    val dx = current.x - last.x
    val dy = current.y - last.y

    val distance = sqrt(
        dx * dx + dy * dy
    )

    return distance > 5f
}
fun isPointInsidePolygon(
    pointX: Float,
    pointY: Float,
    polygon: List<MapPoint>,
    width: Float,
    height: Float
): Boolean {

    var inside = false
    var j = polygon.lastIndex

    for (i in polygon.indices) {

        val xi = polygon[i].x * width
        val yi = polygon[i].y * height

        val xj = polygon[j].x * width
        val yj = polygon[j].y * height

        val intersect =
            ((yi > pointY) != (yj > pointY)) &&
                    (
                            pointX <
                                    (xj - xi) *
                                    (pointY - yi) /
                                    (yj - yi) +
                                    xi
                            )

        if (intersect) {
            inside = !inside
        }

        j = i
    }

    return inside
}