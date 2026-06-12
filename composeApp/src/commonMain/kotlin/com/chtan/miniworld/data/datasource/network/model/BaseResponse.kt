@file:OptIn(kotlinx.serialization.ExperimentalSerializationApi::class)

package com.chtan.miniworld.data.datasource.network.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import kotlinx.serialization.json.JsonNames

@Serializable
data class BaseResponse<T>(
    @SerialName("success")
    @JsonNames("Success")
    val success: Boolean = false,
    
    @SerialName("message")
    @JsonNames("Message")
    val message: String = "",
    
    @SerialName("error")
    @JsonNames("Error")
    val error: String? = null,
    
    @SerialName("data")
    @JsonNames("Data")
    val data: T? = null
)
