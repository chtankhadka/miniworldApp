package com.chtan.miniworld.presentation.user.clanOverview.startOverview

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CardTravel
import androidx.compose.material.icons.filled.Speed


data class UserStartOverviewState(
    val trackDetails: List<UserStartOverviewTrackDetails> = emptyList(),
    val vehicleDetails: List<UserStartOverviewVehicleDetails> = listOf(
        UserStartOverviewVehicleDetails(
            id = "1", detail = listOf(
                VehicleDetail("Top Speed", "100", Icons.Default.Speed),
                VehicleDetail("Acc", "5.6", Icons.Default.CardTravel),
                VehicleDetail("Handling", "6.8", Icons.Default.Speed),
                VehicleDetail("Rating", "5.0", Icons.Default.Speed),
            )
        ), UserStartOverviewVehicleDetails(
            id = "2", detail = listOf(
                VehicleDetail("Top Speed", "90", Icons.Default.Speed),
                VehicleDetail("Acc", "6.6", Icons.Default.Speed),
                VehicleDetail("Handling", "7.8", Icons.Default.Speed),
                VehicleDetail("Rating", "4.0", Icons.Default.Speed),

                )
        ), UserStartOverviewVehicleDetails(
            id = "3", detail = listOf(
                VehicleDetail("Top Speed", "150", Icons.Default.Speed),
                VehicleDetail("Acc", "8.6", Icons.Default.Speed),
                VehicleDetail("Handling", "3.8", Icons.Default.Speed),
                VehicleDetail("Rating", "8.0", Icons.Default.Speed),

                )
        ), UserStartOverviewVehicleDetails(
            id = "4", detail = listOf(
                VehicleDetail("Speed", "200", Icons.Default.Speed),
                VehicleDetail("Acc", "3.6", Icons.Default.Speed),
                VehicleDetail("Handling", "4.8", Icons.Default.Speed),
                VehicleDetail("Rating", "4.0", Icons.Default.Speed),
            )
        )


    )
)
