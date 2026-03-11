package com.chtan.miniworld.presentation.user.drive

import DriveControlDto

sealed interface DriveEvent {
    data object ConnectToCar: DriveEvent
    data class ConnectToCam(val value: Boolean): DriveEvent
    data class OnChangeGear(val value: Int): DriveEvent
    data class OnChangeSteering(val value: Int): DriveEvent
    data class OnChangeAccelerator(val value: Int): DriveEvent
    data class OnChangeBreak(val value: Boolean): DriveEvent
    data class OnChangeMode(val value: String): DriveEvent

}