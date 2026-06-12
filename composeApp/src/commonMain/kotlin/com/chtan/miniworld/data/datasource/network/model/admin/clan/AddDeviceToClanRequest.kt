package com.chtan.miniworld.data.datasource.network.model.admin.clan


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class AddDeviceToClanRequest(
    @SerialName("clan_id") val clanId: String,
    @SerialName("color") val color: String,
    @SerialName("device_name") val deviceName: String,
    @SerialName("device_type") val deviceType: String,
    @SerialName("password") val password: String,
    @SerialName("vehicle_type") val vehicleType: String
)