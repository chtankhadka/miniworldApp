package com.chtan.miniworld.data.repositoryImpl

import DriveControlDto
import com.chtan.miniworld.data.datasource.local.UserLocalDataSource
import com.chtan.miniworld.data.datasource.network.UserRemoteDataSource
import com.chtan.miniworld.data.datasource.network.model.authorization.SignInRequestModel
import com.chtan.miniworld.data.datasource.network.model.authorization.SignInResponseModel
import com.chtan.miniworld.data.datasource.network.result.DataError
import com.chtan.miniworld.data.datasource.network.websockets.ImageWebSocketDataSource
import com.chtan.miniworld.data.datasource.network.websockets.WebSocketEvent
import com.chtan.miniworld.domain.repository.SocketRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.StateFlow
import org.chtan.personalwork.core.domain.Result

class SocketRepositoryImpl(
    private val userRemoteDataSource: UserRemoteDataSource,
    private val userLocalDataSource: UserLocalDataSource,
    private val imageWebSocketDataSource: ImageWebSocketDataSource
): SocketRepository  {

    override val messages: Flow<String> = imageWebSocketDataSource.incomingMessages
    override val latestFrameBytes: Flow<ByteArray?> = imageWebSocketDataSource.latestFrameBytes
    override val connected: StateFlow<Boolean> = imageWebSocketDataSource.connectionState
    override val events: Flow<WebSocketEvent> = imageWebSocketDataSource.events


    override suspend fun connectImageWebSocket() = imageWebSocketDataSource.startImageSocketConnection()
    override suspend fun sendMessage(message: DriveControlDto) = imageWebSocketDataSource.sendMessage(message)
    override suspend fun disconnect() = imageWebSocketDataSource.closeConnection()
    override suspend fun signIn(data: SignInRequestModel): Result<SignInResponseModel, DataError.Remote> {
        return userRemoteDataSource.signIn(data)
    }
    override suspend fun startSocketConnection(
        onTextReceived: (String) -> Unit,
        onClose: (String) -> Unit
    ) {
        return userRemoteDataSource.startSocketConnection(onTextReceived,onClose)
    }
}