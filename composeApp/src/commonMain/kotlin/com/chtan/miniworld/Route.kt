package com.chtan.miniworld

import kotlinx.serialization.Serializable

sealed interface Route {
    @Serializable
    data object Signup: Route

    @Serializable
    data object SignIn: Route

    @Serializable
    data object UserHome: Route



    @Serializable
    data object Map: Route

    @Serializable
    data object UserDashboard: Route


    @Serializable
    data object UserClanOverview: Route

    @Serializable
    data object Derive: Route


    @Serializable
    data class Message(val id: String): Route





    // Admin temp

    @Serializable
    data object AdminCreateClan: Route


    @Serializable
    data object AdminClanOverview: Route

}



