package com.chtan.miniworld.data.datasource.network.websockets

sealed class WebSocketEvent {
    object Connected : WebSocketEvent()
    data class Closed(val reason: String) : WebSocketEvent()
    data class Error(val message: String) : WebSocketEvent()
}