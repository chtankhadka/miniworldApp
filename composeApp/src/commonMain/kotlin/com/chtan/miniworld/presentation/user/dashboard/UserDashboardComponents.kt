package com.chtan.miniworld.presentation.user.dashboard

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Build
import androidx.compose.material.icons.filled.Event
import androidx.compose.material.icons.filled.HolidayVillage
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Leaderboard
import androidx.compose.material.icons.filled.Map
import androidx.compose.material.icons.filled.MapsUgc
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material.icons.filled.Villa
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp
import com.chtan.miniworld.presentation.components.shapes.RoundedParallelogram
import kotlinx.serialization.Serializable

@Composable
fun TitleParallelogramBox(
    number: String, currencyIcon: ImageVector, type: String, onClickAdd: () -> Unit
) {
    val parallelogramShape = RoundedParallelogram(
        radius = 5.dp, skew = -0.1f
    )
    Box(
        modifier = Modifier.wrapContentSize().background(
            color = MaterialTheme.colorScheme.onBackground, shape = parallelogramShape
        ).padding(
            horizontal = 5.dp, vertical = 3.dp

        )

    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Icon(
                imageVector = currencyIcon,
                tint = MaterialTheme.colorScheme.primary,
                contentDescription = type
            )
            Text(text = number, color = Color.White)
            Spacer(modifier = Modifier.size(5.dp))
            Button(
                modifier = Modifier.padding(2.dp)
                    .defaultMinSize(
                        minWidth = 1.dp,
                        minHeight = 1.dp
                    ),
                shape = RoundedParallelogram(5.dp),
                onClick = {
                    onClickAdd()
                },
                contentPadding = PaddingValues(0.dp)

            ) {
                Icon(
                    modifier = Modifier.size(24.dp),
                    imageVector = Icons.Filled.Add,
                    contentDescription = "Add"
                )

            }
        }
    }
}

sealed class UserDashboardNavBarItem(
    val route: Any,
    val title: String,
    val icon: ImageVector
){

    data object Home: UserDashboardNavBarItem(UserDashboardRoute.Home, "Home", Icons.Filled.Home)
    data object Maps: UserDashboardNavBarItem(UserDashboardRoute.Map, "Maps", Icons.Filled.Map)
    data object Events: UserDashboardNavBarItem(UserDashboardRoute.Events, "Events", Icons.Filled.Event)
    data object Leaderboard: UserDashboardNavBarItem(UserDashboardRoute.Leaderboard, "Leaderboard", Icons.Filled.Leaderboard)
    data object Settings: UserDashboardNavBarItem(UserDashboardRoute.Setting, "Settings", Icons.Filled.Settings)






// Admin
    data object EditMap: UserDashboardNavBarItem(UserDashboardRoute.EditMap, "Edit Map", Icons.Filled.MapsUgc)
    data object CreateClan: UserDashboardNavBarItem(UserDashboardRoute.CreateClan, "Create Clan", Icons.Filled.Build)
    data object ClanOverview: UserDashboardNavBarItem(UserDashboardRoute.ClanOverview, "Clan Overview", Icons.Filled.HolidayVillage)



}

@Serializable
sealed interface UserDashboardRoute {
    @Serializable
    data object Home : UserDashboardRoute

    @Serializable
    data object Map : UserDashboardRoute

    @Serializable
    data object Events : UserDashboardRoute

    @Serializable
    data object Leaderboard : UserDashboardRoute

    @Serializable
    data object Setting : UserDashboardRoute

    @Serializable
    data object EditMap : UserDashboardRoute

    @Serializable
    data object CreateClan : UserDashboardRoute

    @Serializable
    data object ClanOverview : UserDashboardRoute

}