package com.chtan.miniworld.data.datasource.network.model.admin.clan

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class CreateClanRequest(
    @SerialName("name")
    val name: String,
    @SerialName("tag")
    val tag: String,
    @SerialName("description")
    val description: String?,
    @SerialName("device_ids")
    val deviceIds: List<String> = emptyList()
)

