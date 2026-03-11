package com.chtan.miniworld.data.datasource.network.model.authorization

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class SignInRequestModel(
    @SerialName("email")
    val userMail: String,
    @SerialName("password")
    val password: String
)