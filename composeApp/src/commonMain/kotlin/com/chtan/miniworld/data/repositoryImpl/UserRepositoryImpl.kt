package com.chtan.miniworld.data.repositoryImpl

import DriveControlDto
import com.chtan.miniworld.data.datasource.local.UserLocalDataSource
import com.chtan.miniworld.data.datasource.network.UserRemoteDataSource
import com.chtan.miniworld.data.datasource.network.websockets.DriveWebSocketDataSource
import com.chtan.miniworld.data.datasource.network.model.authorization.SignInRequestModel
import com.chtan.miniworld.data.datasource.network.model.authorization.SignInResponseModel
import com.chtan.miniworld.data.datasource.network.result.DataError
import com.chtan.miniworld.data.datasource.network.websockets.WebSocketEvent
import com.chtan.miniworld.domain.repository.UserRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.StateFlow
import org.chtan.personalwork.core.domain.Result

class UserRepositoryImpl(
    private val userRemoteDataSource: UserRemoteDataSource,
    private val userLocalDataSource: UserLocalDataSource,
    private val driveWebSocketDataSource: DriveWebSocketDataSource
): UserRepository  {

    override val messages: Flow<String> = driveWebSocketDataSource.incomingMessages
    override val connected: StateFlow<Boolean> = driveWebSocketDataSource.connectionState
    override val events: Flow<WebSocketEvent> = driveWebSocketDataSource.events


    override suspend fun connectDriveWebSocket() = driveWebSocketDataSource.startSocketConnection()
    override suspend fun sendMessage(message: DriveControlDto) = driveWebSocketDataSource.sendMessage(message)
    override suspend fun disconnect() = driveWebSocketDataSource.closeConnection()
    override suspend fun signIn(data: SignInRequestModel): Result<SignInResponseModel, DataError.Remote> {
        return userRemoteDataSource.signIn(data)
    }
}