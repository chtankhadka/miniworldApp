package com.chtan.miniworld.data.datasource.network.result

sealed interface DataError: Error {
    sealed interface Remote: DataError {
        data object REQUEST_TIMEOUT : Remote
        data object TOO_MANY_REQUESTS : Remote
        data object NO_INTERNET : Remote
        data object SERVER : Remote
        data object SERIALIZATION : Remote

        data object UNAUTHORIZED: Remote
        data object FORBIDDEN: Remote
        data object NOT_FOUND: Remote
        data object UNKNOWN : Remote
        data class BackendError(val message: String) : Remote
    }

    enum class Local: DataError {
        DISK_FULL,
        UNKNOWN
    }
}
