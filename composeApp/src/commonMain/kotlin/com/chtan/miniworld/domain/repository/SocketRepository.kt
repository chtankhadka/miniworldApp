package com.chtan.miniworld.domain.repository

import com.chtan.miniworld.data.datasource.network.model.DriveControlDto
import com.chtan.miniworld.data.datasource.network.model.authorization.SignInRequestModel
import com.chtan.miniworld.data.datasource.network.model.authorization.SignInResponseModel
import com.chtan.miniworld.data.datasource.network.websockets.WebSocketEvent
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.StateFlow
import com.chtan.miniworld.data.datasource.network.result.RemoteResult

interface SocketRepository {

    val messages: Flow<String>
    val latestFrameBytes: Flow<ByteArray?>
    val connected: StateFlow<Boolean>
    val events: Flow<WebSocketEvent>


    suspend fun connectImageWebSocket()
    suspend fun sendMessage(message: DriveControlDto)
    suspend fun disconnect()


    suspend fun signIn(data: SignInRequestModel): RemoteResult<SignInResponseModel>


    suspend fun startSocketConnection(onTextReceived: (String) -> Unit, onClose : (value: String) -> Unit)
}
