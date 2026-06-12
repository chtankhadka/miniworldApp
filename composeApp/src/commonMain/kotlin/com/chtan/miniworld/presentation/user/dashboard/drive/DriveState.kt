package com.chtan.miniworld.presentation.user.dashboard.drive

import com.chtan.miniworld.data.datasource.network.model.DriveControlDto

data class DriveState(
    val isConnected: Boolean = false,
    val isCamConnected: Boolean = false,
    val driveControlDto: DriveControlDto = DriveControlDto()
)
