package com.chtan.miniworld.data.datasource.network

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import com.chtan.miniworld.data.datasource.local.StoredData
import com.chtan.miniworld.data.datasource.network.api.ApiEndPoints.SIGN_IN
import com.chtan.miniworld.data.datasource.network.model.authorization.SignInRequestModel
import com.chtan.miniworld.data.datasource.network.model.authorization.SignInResponseModel
import com.chtan.miniworld.data.datasource.network.result.DataError
import io.ktor.client.HttpClient
import io.ktor.client.plugins.websocket.DefaultClientWebSocketSession
import io.ktor.client.plugins.websocket.webSocket
import io.ktor.client.request.header
import io.ktor.client.request.post
import io.ktor.client.request.setBody
import io.ktor.http.ContentType
import io.ktor.http.HttpHeaders
import io.ktor.http.contentType
import io.ktor.websocket.Frame
import io.ktor.websocket.readReason
import io.ktor.websocket.readText
import org.chtan.personalwork.core.data.responseToResult
import org.chtan.personalwork.core.data.safeCall
import org.chtan.personalwork.core.domain.Result

class UserRemoteDataSource(
    private val httpClient: HttpClient,
    preferences: DataStore<Preferences>
) {
    private var session: DefaultClientWebSocketSession? = null
    private val userData = StoredData(preferences)


    private suspend inline fun <reified T> authorizeRequest(
        endpoint: String,
        body: Any? = null
    ): Result<T, DataError.Remote> {
        return safeCall(execute = {
            httpClient.post(urlString = endpoint) {
                contentType(ContentType.Application.Json)
                body?.let { setBody(it) }
                header(HttpHeaders.Authorization, "Bearer ")
            }

        }, responseMapper = { response ->
            responseToResult(response)
        })
    }

    private suspend inline fun <reified T> publicRequest(
        endpoint: String,
        body: Any? = null
    ): Result<T, DataError.Remote> {
        return safeCall(execute = {
            httpClient.post(urlString = endpoint) {
                contentType(ContentType.Application.Json)
                body?.let { setBody(it) }
            }

        }, responseMapper = { response ->
            responseToResult(response)
        })
    }
    suspend fun startSocketConnection(
        onTextReceived: (value: String) -> Unit,
        onClose: (value: String) -> Unit,
    ) {
        val token = userData.getToken()
        try {
            httpClient.webSocket(urlString = "ws://192.168.0.234:8000/ws/user",
                request = {
                    contentType(ContentType.Application.Json)
                    header(HttpHeaders.Authorization, "Bearer $token")
                }) {
                session = this
                println("WebSocket connection established")
                try {
                    for (messages in incoming) {
                        when (messages) {
                            is Frame.Binary -> TODO()
                            is Frame.Close -> onClose(messages.readReason().toString())
                            is Frame.Ping -> TODO()
                            is Frame.Pong -> TODO()
                            is Frame.Text -> {
                                onTextReceived(messages.readText())
                            }

                            else -> TODO()
                        }
                    }

                } catch (e: Exception) {
                    println(e.message)
                }
            }
        }catch (e: Exception){
            println(e)
        }

    }


    suspend fun signIn(data: SignInRequestModel): Result<SignInResponseModel, DataError.Remote> {
        return publicRequest(SIGN_IN, data)
    }

}