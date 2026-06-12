package com.chtan.miniworld.presentation.user.clanOverview

import kotlinx.serialization.Serializable

@Serializable
sealed interface UserClanOverviewRoute{
    @Serializable
    data object SelectVehicle: UserClanOverviewRoute
    @Serializable
    data object StartOverview: UserClanOverviewRoute
}