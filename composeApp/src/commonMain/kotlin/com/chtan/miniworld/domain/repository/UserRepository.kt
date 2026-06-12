package com.chtan.miniworld.domain.repository

import com.chtan.miniworld.data.datasource.network.model.DriveControlDto
import com.chtan.miniworld.data.datasource.network.model.authorization.SignInRequestModel
import com.chtan.miniworld.data.datasource.network.model.authorization.SignInResponseModel
import com.chtan.miniworld.data.datasource.network.websockets.WebSocketEvent
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.StateFlow
import com.chtan.miniworld.data.datasource.network.result.RemoteResult

interface UserRepository {
    val messages: Flow<String>
    val connected: StateFlow<Boolean>
    val events: Flow<WebSocketEvent>

    suspend fun connectDriveWebSocket()
    suspend fun sendMessage(message: DriveControlDto)
    suspend fun disconnect()

    suspend fun signIn(data: SignInRequestModel): RemoteResult<SignInResponseModel>
}
