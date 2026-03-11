package com.chtan.miniworld.data.datasource.network.model.authorization

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class SignInResponseModel(
    @SerialName("data")
    val data: Data,
    @SerialName("error")
    val error: String?,
    @SerialName("message")
    val message: String,
    @SerialName("success")
    val success: Boolean
){
    @Serializable
    data class Data(
    @SerialName("access_token")
    val accessToken: String,
    @SerialName("refresh_token")
    val refreshToken: String,
    @SerialName("id")
    val userId: String
)}