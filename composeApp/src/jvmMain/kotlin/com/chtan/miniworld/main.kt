package com.chtan.miniworld

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.key.Key
import androidx.compose.ui.input.key.KeyEventType
import androidx.compose.ui.input.key.key
import androidx.compose.ui.input.key.onPreviewKeyEvent
import androidx.compose.ui.input.key.type
import androidx.compose.ui.unit.DpSize
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Window
import androidx.compose.ui.window.WindowPlacement
import androidx.compose.ui.window.application
import androidx.compose.ui.window.rememberWindowState
import com.chtan.miniworld.di.initKoinJVM
import io.github.vinceglb.filekit.FileKit

val koin = initKoinJVM()

fun main() = application {
    val windowState = rememberWindowState(
        placement = WindowPlacement.Floating, // start in fullscreen
        size = DpSize(500.dp, 300.dp)           // fallback size
    )


    FileKit.init(appId = "MyApplication")
    Window(
        onCloseRequest = ::exitApplication,
        alwaysOnTop = true,
        title = "miniworld",
        state = windowState

    ) {
        Box(modifier = Modifier
            .fillMaxSize()
            .onPreviewKeyEvent { event ->
                if (event.type == KeyEventType.KeyUp && event.key == Key.Escape) {
                    windowState.placement = WindowPlacement.Floating // exit fullscreen
                    true
                } else {
                    false
                }
            }){
            App()
        }

    }
}