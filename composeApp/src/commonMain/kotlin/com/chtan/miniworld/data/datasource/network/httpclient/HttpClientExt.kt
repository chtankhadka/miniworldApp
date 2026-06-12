package com.chtan.miniworld.data.datasource.network.httpclient

import com.chtan.miniworld.data.datasource.network.model.BaseResponse
import com.chtan.miniworld.data.datasource.network.result.DataError
import com.chtan.miniworld.data.datasource.network.result.RemoteResult
import io.ktor.client.call.body
import io.ktor.client.network.sockets.SocketTimeoutException
import io.ktor.client.statement.HttpResponse
import io.ktor.client.statement.bodyAsText
import io.ktor.util.network.UnresolvedAddressException
import kotlinx.coroutines.currentCoroutineContext
import kotlinx.coroutines.ensureActive
import kotlinx.serialization.SerializationException
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.jsonObject
import kotlinx.serialization.json.jsonPrimitive

val networkJson = Json { ignoreUnknownKeys = true }

suspend inline fun <reified T> safeCall(
    execute: suspend () -> HttpResponse,
    responseMapper: suspend (HttpResponse) -> RemoteResult<T>
): RemoteResult<T> {
    return try {
        val response = execute()
        responseMapper(response)
    } catch (e: SocketTimeoutException) {
        RemoteResult.Error(DataError.Remote.REQUEST_TIMEOUT)
    } catch (e: UnresolvedAddressException) {
        RemoteResult.Error(DataError.Remote.NO_INTERNET)
    } catch (e: SerializationException) {
        RemoteResult.Error(DataError.Remote.SERIALIZATION)
    } catch (e: Exception) {
        currentCoroutineContext().ensureActive()
        RemoteResult.Error(DataError.Remote.UNKNOWN)
    }
}

suspend inline fun <reified T> responseToResult(
    response: HttpResponse
): RemoteResult<T> {
    return when (response.status.value) {
        in 200..299 -> {
            try {
                val body = response.body<T>()
                if (body is BaseResponse<*> && !body.success) {
                    RemoteResult.Error(DataError.Remote.BackendError(body.error ?: body.message))
                } else {
                    RemoteResult.Success(body)
                }
            } catch (e: Exception) {
                RemoteResult.Error(DataError.Remote.SERIALIZATION)
            }
        }

        401 -> RemoteResult.Error(DataError.Remote.UNAUTHORIZED)
        403 -> RemoteResult.Error(DataError.Remote.FORBIDDEN)
        404 -> RemoteResult.Error(DataError.Remote.NOT_FOUND)
        408 -> RemoteResult.Error(DataError.Remote.REQUEST_TIMEOUT)
        429 -> RemoteResult.Error(DataError.Remote.TOO_MANY_REQUESTS)
        in 500..599 -> RemoteResult.Error(DataError.Remote.SERVER)
        else -> {
            RemoteResult.Error(DataError.Remote.BackendError(response.extractErrorMessage()))
        }
    }
}

/**
 * Robustly attempts to extract an error message from the response body.
 */
suspend fun HttpResponse.extractErrorMessage(): String {
    val text = try { bodyAsText() } catch (e: Exception) { null }
    
    if (text.isNullOrBlank()) return status.description

    return try {
        val jsonElement = networkJson.parseToJsonElement(text)
        val obj = jsonElement.jsonObject
        obj["error"]?.jsonPrimitive?.content
            ?: obj["Error"]?.jsonPrimitive?.content
            ?: obj["message"]?.jsonPrimitive?.content
            ?: obj["Message"]?.jsonPrimitive?.content
            ?: text
    } catch (e: Exception) {
        text
    }
}
