package com.chtan.miniworld.data.datasource.network.model.authorization

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class SignupRequestModel(
    @SerialName("first_name")
    val firstName : String,
    @SerialName("last_name")
    val lastName : String,
    @SerialName("password")
    val password: String,
    @SerialName("email")
    val email: String
)
