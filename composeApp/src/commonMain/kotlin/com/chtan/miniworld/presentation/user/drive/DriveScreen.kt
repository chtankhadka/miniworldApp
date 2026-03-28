package com.chtan.miniworld.presentation.user.drive

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.detectDragGestures
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CarRental
import androidx.compose.material3.Button
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.modifier.modifierLocalConsumer
import androidx.compose.ui.platform.LocalLayoutDirection
import androidx.compose.ui.unit.IntSize
import androidx.compose.ui.unit.LayoutDirection
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.times
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavHostController
import com.chtan.miniworld.presentation.user.components.Break
import com.chtan.miniworld.presentation.user.components.GearChange
import com.chtan.miniworld.presentation.user.components.GearSelectorRow
import com.chtan.miniworld.presentation.user.components.HorizontalAutoCenterSlider
import com.chtan.miniworld.presentation.user.components.VerticalAcceleratorPedal
import com.chtan.miniworld.presentation.user.drive.selectdevices.NavDevicesScreen
import com.multiplatform.webview.web.WebView
import com.multiplatform.webview.web.rememberWebViewState
import kotlinx.coroutines.launch
import miniworld.composeapp.generated.resources.Res
import miniworld.composeapp.generated.resources.car2
import miniworld.composeapp.generated.resources.carConnected
import org.jetbrains.compose.resources.painterResource
import kotlin.math.abs

enum class SwipeDirection {
    LEFT,
    RIGHT,
    UP,
    DOWN,
    NONE
}
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DriveScreen(
    nav: NavHostController,
    event: (DriveEvent) -> Unit,
    state: DriveState,
    viewModel: DriveViewModel
) {

    var drawerState = rememberDrawerState(DrawerValue.Closed)
    var boxSize by remember { mutableStateOf(IntSize.Zero) }
    val coroutineScope = rememberCoroutineScope()
    val webViewState = rememberWebViewState(
        url = "http://192.168.0.12:8080/stream/693421d75a4cfba1adfaaa0e",
        extraSettings = {
            isJavaScriptEnabled = true
            supportZoom = true
        }
    )



    val imageBytes by viewModel.latestFrameBytes.collectAsStateWithLifecycle(null)
    ModalNavigationDrawer(

        drawerState = drawerState, drawerContent = {
            // Right drawer content (slides from right)
            CompositionLocalProvider(LocalLayoutDirection provides LayoutDirection.Ltr) {
                Column(
                    modifier = Modifier.fillMaxHeight().width(280.dp).background(Color.LightGray)
                        .padding(16.dp),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    NavDevicesScreen(drawerState,coroutineScope)
                }
            }
        }, gesturesEnabled = false, modifier = Modifier.fillMaxSize()
    ) {

        // Scaffold content (main screen)
        Scaffold(
            modifier = Modifier.background(Color.Transparent) ,
            topBar = {
                Row(modifier = Modifier.fillMaxWidth()) {
                    IconButton(
                        onClick = {
                            coroutineScope.launch {
                                if (drawerState.isOpen) drawerState.close() else drawerState.open()
                            }
                        },
                        content = {
                            Icon(
                                imageVector = Icons.Default.CarRental,
                                contentDescription = "Change Car"
                            )
                        }
                    )
                    Box(
                        modifier = Modifier.fillMaxWidth()
                            .horizontalScroll(rememberScrollState()) // Scroll the whole Box horizontally
                            .padding(8.dp)
                    )
                    {
                        Row(

                            horizontalArrangement = Arrangement.spacedBy(8.dp)
                        ) {
                            Spacer(modifier = Modifier.width(5 * (60.dp + 8.dp))) // same as 5 buttons with spacing


                            Box(modifier = Modifier.size(60.dp).clickable {
                                event(DriveEvent.ConnectToCar)
                            }) {
                                Image(
                                    modifier = Modifier.fillMaxSize().clickable {
                                        if (!state.isConnected) {
                                            event(DriveEvent.ConnectToCar)
                                        }
                                    },
                                    painter = painterResource(Res.drawable.carConnected),
                                    contentDescription = null
                                )
                                if (!state.isConnected) {
                                    Canvas(
                                        modifier = Modifier.fillMaxSize(), onDraw = {
                                            drawLine(
                                                color = Color.Black,
                                                strokeWidth = 10f,
                                                start = Offset(0f, 0f),
                                                end = Offset(size.width, size.height)
                                            )
                                        })
                                }

                            }
                            Box(modifier = Modifier.size(60.dp).clickable {
                                event(DriveEvent.ConnectToCar)
                            }) {
                                Image(
                                    modifier = Modifier.fillMaxSize().clickable {
                                        event(DriveEvent.ConnectToCam(state.isCamConnected))
                                    },
                                    painter = painterResource(Res.drawable.car2),
                                    contentDescription = null
                                )
                                if (!state.isCamConnected) {
                                    Canvas(
                                        modifier = Modifier.fillMaxSize(), onDraw = {
                                            drawLine(
                                                color = Color.Black,
                                                strokeWidth = 10f,
                                                start = Offset(0f, 0f),
                                                end = Offset(size.width, size.height)
                                            )
                                        })
                                }

                            }

                            repeat(20) { index ->
                                Button(
                                    onClick = {},
                                    shape = CircleShape,
                                    modifier = Modifier.size(60.dp)
                                ) {
                                    Text("car")
                                }
                            }
                        }
                    }
                }

            }) { innerPadding ->
            WebView(
                state = webViewState,
                modifier = Modifier.fillMaxSize()
            )
            // Main conten
            var x: Float by  remember { mutableStateOf(0f)}
            var y: Float by remember { mutableStateOf(0f)}
            Box(
                modifier = Modifier.fillMaxSize().padding(innerPadding).padding(16.dp).background(Color.Transparent)
                    .pointerInput(Unit) {
                        detectDragGestures (
                            onDragEnd = {
                                event(DriveEvent.OnXYChange(
                                    isXYChange = false,
                                    x = 4915,
                                    y = 4915))
                            }

                        ){ change, dragAmount ->

                            x = dragAmount.x
                            y = dragAmount.y
                            change.consume()

                            val isHorizontal = abs(dragAmount.x) > abs(dragAmount.y)
                            val direction = when {
                                isHorizontal && dragAmount.x > 0  -> SwipeDirection.RIGHT
                                isHorizontal && dragAmount.x < 0  -> SwipeDirection.LEFT
                                !isHorizontal && dragAmount.y < 0 -> SwipeDirection.UP
                                !isHorizontal && dragAmount.y > 0 -> SwipeDirection.DOWN
                                else                               -> SwipeDirection.NONE
                            }

                            val xValue = when (direction) {
                                SwipeDirection.LEFT  -> 5788
                                SwipeDirection.RIGHT -> 3713
                                else                 -> 4915  // neutral when moving vertically
                            }

                            val yValue = when (direction) {
                                SwipeDirection.UP   -> 3713
                                SwipeDirection.DOWN -> 5788
                                else                -> 4915  // neutral when moving horizontally
                            }

                            event(DriveEvent.OnXYChange(
                                isXYChange = true,
                                x = xValue,
                                y = yValue
                            ))

                        }
                    }
                    .onGloballyPositioned { layoutCoordinates ->
                        boxSize = layoutCoordinates.size  // width & height (px)
                    },
                contentAlignment = Alignment.Center
            ) {
                Text(text = "")

                Box(modifier = Modifier.align(Alignment.TopEnd).padding(10.dp)) {
                    Column {
                        GearSelectorRow(
                            trackWidth = (boxSize.width.dp / 10),
                            value = state.driveControlDto.m,
                            onValueChange = {
                                event(DriveEvent.OnChangeMode(it))
                            })

                        GearChange(
                            gear = state.driveControlDto.g,
                            onClickGear = { gear ->
                                event(DriveEvent.OnChangeGear(gear))
                            },
                        )
                    }

                }

                Box(modifier = Modifier.align(Alignment.BottomStart).padding(10.dp)) {
                    HorizontalAutoCenterSlider {
                        event(DriveEvent.OnChangeSteering(it))

                    }
                }
                Box(modifier = Modifier.align(Alignment.BottomEnd).padding(10.dp)) {
                    Row(verticalAlignment = Alignment.Bottom) {
                        Break(onClickBreak = {
                            event(DriveEvent.OnChangeBreak(true))
                        })
                        Spacer(Modifier.width(5.dp))
                        VerticalAcceleratorPedal() {
                            event(DriveEvent.OnChangeAccelerator(it))
                        }
                    }

                }

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceAround
                ) {

                }
            }

        }
    }
}

