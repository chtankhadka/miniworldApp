package com.chtan.miniworld.data.datasource.network.websockets

import DriveControlDto
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import com.chtan.miniworld.data.datasource.local.StoredData
import com.chtan.miniworld.data.datasource.network.api.ApiEndPoints.imageSocketUrl
import io.ktor.client.*
import io.ktor.client.plugins.websocket.*
import io.ktor.client.request.header
import io.ktor.http.*
import io.ktor.websocket.*
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.*
import kotlinx.serialization.json.Json

class ImageWebSocketDataSource(
    private val httpClient: HttpClient,
    preferences: DataStore<Preferences>
) {
    private val userData = StoredData(preferences)
    private var session: DefaultClientWebSocketSession? = null
    private var connectionJob: Job? = null

    private val _incomingMessages = MutableSharedFlow<String>()
    val incomingMessages: SharedFlow<String> = _incomingMessages.asSharedFlow()

    private val _connectionState = MutableStateFlow(false)
    val connectionState: StateFlow<Boolean> = _connectionState.asStateFlow()

    private val _events = MutableSharedFlow<WebSocketEvent>()
    val events: SharedFlow<WebSocketEvent> = _events.asSharedFlow()

    // 🆕 Live image flow (JPEG → Bitmap)

    // 🆕 Live image flow (platform-independent)
    private val _latestFrameBytes = MutableStateFlow<ByteArray?>(null)
    val latestFrameBytes: StateFlow<ByteArray?> = _latestFrameBytes.asStateFlow()

    private val reconnectDelay = 5000L // ms
    private val enableAutoReconnect = false // toggle this if desired

    suspend fun startImageSocketConnection(
        onClose: (reason: String) -> Unit = {}
    ) {
        if (connectionJob?.isActive == true) return

        connectionJob = CoroutineScope(Dispatchers.IO).launch {
            val token = userData.getToken()
            var retryCount = 0

            while (isActive) {
                try {
                    httpClient.webSocket(
                        urlString = imageSocketUrl
                        ,
                        request = {
                            contentType(ContentType.Application.Json)
                            header(HttpHeaders.Authorization, "Bearer $token")
                        }
                    ) {
                        session = this
                        _connectionState.value = true
                        _events.emit(WebSocketEvent.Connected)
                        println("✅ WebSocket connected")

                        for (frame in incoming) {
                            when (frame) {
                                is Frame.Text -> {
                                    val msg = frame.readText()
                                    println("📩 Text: $msg")
                                    _incomingMessages.emit(msg)
                                }

                                is Frame.Binary -> {
                                    val bytes = frame.readBytes()
                                    println("📸 Frame received: ${bytes.size} bytes")

                                    // Just emit the raw bytes (no Bitmap conversion)
                                    _latestFrameBytes.value = bytes
                                }

                                is Frame.Close -> {
                                    val reason = frame.readReason()?.message ?: "Closed"
                                    println("🛑 WebSocket closed: $reason")
                                    _connectionState.value = false
                                    _events.emit(WebSocketEvent.Closed(reason))
                                    onClose(reason)
                                    break
                                }

                                else -> {}
                            }
                        }
                    }
                } catch (e: Exception) {
                    println("⚠️ WebSocket error: ${e.message}")
                    _connectionState.value = false
                    _events.emit(WebSocketEvent.Error(e.message ?: "Unknown error"))
                } finally {
                    session = null
                }

                if (!enableAutoReconnect) break

                retryCount++
                println("🔁 Reconnecting in ${reconnectDelay / 1000}s (attempt $retryCount)")
                delay(reconnectDelay)
            }
        }
    }

    suspend fun sendMessage(message: DriveControlDto) {
        session?.send(Frame.Text(Json.encodeToString(message)))
            ?: _events.emit(WebSocketEvent.Error("Cannot send — not connected"))
    }

    suspend fun closeConnection() {
        session?.close(CloseReason(CloseReason.Codes.NORMAL, "Closed by user"))
        session = null
        _connectionState.value = false
        _events.emit(WebSocketEvent.Closed("Closed by user"))
        connectionJob?.cancel()
        println("👋 WebSocket closed by user")
    }

}
