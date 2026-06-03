package com.chtan.miniworld.presentation.user.map

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AreaChart
import androidx.compose.material.icons.filled.Details
import androidx.compose.material.icons.filled.Hardware
import androidx.compose.material.icons.filled.TempleHindu
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.drawOutline
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.ParagraphStyle
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.chtan.miniworld.Route
import com.chtan.miniworld.presentation.components.shapes.RoundedParallelogram
import com.chtan.miniworld.presentation.user.dashboard.UserDashboardState
import miniworld.composeapp.generated.resources.Res
import miniworld.composeapp.generated.resources.testCountryMap
import org.jetbrains.compose.resources.painterResource


@Composable
fun UserMapScreen(state: UserDashboardState, nav: NavHostController) {


    var selectedLandId by remember {
        mutableStateOf<String?>(null)
    }

    Box(Modifier.fillMaxSize().background(color = Color(42, 69, 99))) {
        Row(modifier = Modifier.fillMaxHeight().fillMaxWidth(0.8f).padding(horizontal = 5.dp).align(Alignment.CenterEnd)) {
            Box(
                modifier = Modifier.fillMaxHeight().aspectRatio(1f)
            ) {
                Image(
                    painter = painterResource(Res.drawable.testCountryMap),
                    contentDescription = null,
                    contentScale = ContentScale.FillBounds,
                    modifier = Modifier.fillMaxSize()
                )
                Canvas(
                    modifier = Modifier.fillMaxSize().pointerInput(state.mapLandDetails) {
                        detectTapGestures { tap ->

                            selectedLandId = state.mapLandDetails.firstOrNull { land ->

                                isPointInsidePolygon(
                                    pointX = tap.x,
                                    pointY = tap.y,
                                    polygon = land.points,
                                    width = size.width.toFloat(),
                                    height = size.height.toFloat()
                                )

                            }?.id
                        }
                    }) {
                    state.mapLandDetails.forEach { land ->

                        if (land.points.size < 3) return@forEach

                        val path = Path()

                        land.points.forEachIndexed { index, point ->

                            val x = point.x * size.width
                            val y = point.y * size.height

                            if (index == 0) {
                                path.moveTo(x, y)
                            } else {
                                path.lineTo(x, y)
                            }
                        }

                        path.close()

                        val isSelected = land.id == selectedLandId

                        drawPath(
                            path = path, color = when {
                                isSelected -> Color.White.copy(alpha = 0.1f)

                                else -> Color.Black.copy(alpha = 0.1f)
                            }
                        )

                        drawPath(
                            path = path, color = Color.Transparent, style = Stroke(
                                width = 2f
                            )
                        )
                    }
                }
            }
            Column(modifier = Modifier.weight(1f).padding(start = 5.dp),
                verticalArrangement = Arrangement.spacedBy(2.dp)){
                Row(modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween) {
                    Text(
                        maxLines = 2,
                        text = buildAnnotatedString {
                        withStyle(style = ParagraphStyle(lineHeight = 12.sp)){

                            withStyle(style = SpanStyle(fontSize = 12.sp, fontWeight = FontWeight.Bold, color = Color.LightGray)) {
                                append("Brighton \n")
                            }
                            withStyle(style = SpanStyle(fontSize = 14.sp, color = Color.White)) {
                                append("The Des Island")

                            }
                            withStyle(style = SpanStyle(fontSize = 12.sp, fontWeight = FontWeight.Bold, color = Color.LightGray)) {
                                append("  5m2")
                            }

                        }
                    })
                    Button(
                        enabled = !selectedLandId.isNullOrBlank(),
                        modifier = Modifier.defaultMinSize(0.dp),
                        shape = RoundedParallelogram(5.dp),
                        onClick = {
                            nav.navigate(Route.UserSelectVehicle)

                    },
                        content = {
                            Text(text = "Select Map", maxLines = 1)
                        })
                }
                Card {
                    Text(modifier = Modifier.fillMaxWidth(),
                        text = "Objects",
                        textAlign = TextAlign.Center,
                        fontWeight = FontWeight.Bold
                        )
                    Row(modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceAround) {
                        IconButton(
                            shape = RoundedParallelogram(5.dp),
                            onClick = {

                            },
                            content = {
                                Icon(imageVector = Icons.Default.Details, contentDescription = "Details")
                            }

                        )
                        IconButton(
                            onClick = {

                            },
                            content = {
                                Icon(imageVector = Icons.Default.AreaChart, contentDescription = "Details")
                            }

                        )
                        IconButton(
                            onClick = {

                            },
                            content = {
                                Icon(imageVector = Icons.Default.TempleHindu, contentDescription = "Details")
                            }

                        )
                        IconButton(
                            onClick = {

                            },
                            content = {
                                Icon(imageVector = Icons.Default.Hardware, contentDescription = "Details")
                            }

                        )
                    }
                    //object items
                    Column(modifier = Modifier.weight(1f),
                        verticalArrangement = Arrangement.spacedBy(5.dp)) {
                        ObjectDetails("Difficulty",20)
                        ObjectDetails("Adventure" ,70)
                        ObjectDetails("Busy" ,20)
                        ObjectDetails("Loved" ,80)
                    }


                }
            }

        }

    }


}
@Composable
fun ObjectDetails(
    text: String,
    fillingPercentage: Int
){
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 5.dp)
            .background(
                MaterialTheme.colorScheme.secondaryContainer,
                RoundedParallelogram(5.dp)
            )
    ) {
        Text(
            text = "$text ${fillingPercentage}%",
            modifier = Modifier
                .fillMaxWidth()
                .clip(RoundedParallelogram(5.dp))
                .align(Alignment.Center)
                .drawBehind {
                    val outline = RoundedParallelogram(5.dp)
                        .createOutline(
                            Size(size.width * fillingPercentage/100, size.height),
                            layoutDirection,
                            this
                        )

                    drawOutline(
                        outline = outline,
                        color = Color.Red
                    )
                },
            textAlign = TextAlign.Center
        )
    }
}