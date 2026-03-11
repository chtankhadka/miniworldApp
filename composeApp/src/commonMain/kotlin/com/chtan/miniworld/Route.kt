package com.chtan.miniworld

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

sealed interface Route {
    @Serializable
    data object Signup: Route

    @Serializable
    data object SignIn: Route

    @Serializable
    data object Chat: Route

    @Serializable
    data object Map: Route

    @Serializable
    data object Dashboard: Route

    @Serializable
    data object Derive: Route


    @Serializable
    data class Message(val id: String): Route
}