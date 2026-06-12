package com.chtan.miniworld.presentation.admin.clan.clanoverview

import com.chtan.miniworld.data.datasource.network.model.admin.clan.AddDeviceToClanRequest

sealed interface AdminClanOverviewEvent {
    data class AddDeviceInClan(val deviceDetails: AddDeviceToClanRequest) : AdminClanOverviewEvent
    data object DismissError : AdminClanOverviewEvent

}