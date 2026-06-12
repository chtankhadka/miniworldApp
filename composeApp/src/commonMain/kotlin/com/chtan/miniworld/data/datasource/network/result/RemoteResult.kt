package com.chtan.miniworld.data.datasource.network.result

sealed interface RemoteResult<out D> {
    data class Success<out D>(val data: D): RemoteResult<D>
    data class Error(val error: DataError.Remote): RemoteResult<Nothing>
}

inline fun <T, R> RemoteResult<T>.map(map: (T) -> R): RemoteResult<R> {
    return when(this) {
        is RemoteResult.Error -> RemoteResult.Error(error)
        is RemoteResult.Success -> RemoteResult.Success(map(data))
    }
}

fun <T> RemoteResult<T>.asEmptyDataRemoteResult(): EmptyRemoteResult {
    return map {  }
}

inline fun <T> RemoteResult<T>.onSuccess(action: (T) -> Unit): RemoteResult<T> {
    return when(this) {
        is RemoteResult.Error -> this
        is RemoteResult.Success -> {
            action(data)
            this
        }
    }
}

inline fun <T> RemoteResult<T>.onError(action: (DataError.Remote) -> Unit): RemoteResult<T> {
    return when(this) {
        is RemoteResult.Error -> {
            action(error)
            this
        }
        is RemoteResult.Success -> this
    }
}

typealias EmptyRemoteResult = RemoteResult<Unit>
