package com.chtan.miniworld.presentation.user.drive

import DriveControlDto

data class DriveState(
    val isConnected: Boolean = false,
    val isCamConnected: Boolean = false,
    val driveControlDto: DriveControlDto = DriveControlDto()
)
