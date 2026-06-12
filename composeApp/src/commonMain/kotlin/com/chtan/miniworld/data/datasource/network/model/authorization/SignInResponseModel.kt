package com.chtan.miniworld.data.datasource.network.model.authorization

import com.chtan.miniworld.data.datasource.network.model.BaseResponse
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

typealias SignInResponseModel = BaseResponse<SignInData>

@Serializable
data class SignInData(
    @SerialName("access_token")
    val accessToken: String,
    @SerialName("refresh_token")
    val refreshToken: String,
    @SerialName("id")
    val userId: String
)
