package com.chtan.miniworld.presentation.user.clanOverview

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.chtan.miniworld.presentation.user.clanOverview.selectVehicle.UserSelectVehicleScreen
import com.chtan.miniworld.presentation.user.clanOverview.selectVehicle.UserSelectVehicleTopAppBar
import com.chtan.miniworld.presentation.user.clanOverview.selectVehicle.UserSelectVehicleViewModel
import com.chtan.miniworld.presentation.user.clanOverview.startOverview.StartOverviewScreen
import com.chtan.miniworld.presentation.user.clanOverview.startOverview.UserStartOverviewViewModel
import org.koin.compose.viewmodel.koinViewModel

@Composable
fun UserClanOverviewScreen(
    nav: NavHostController,
    event: (UserClanOverviewEvent) -> Unit,
    state: UserClanOverviewState
) {
    val bottomNavController = rememberNavController()
    val navBackStackEntry by bottomNavController.currentBackStackEntryAsState()
    Scaffold(
        modifier = Modifier.fillMaxSize(), topBar = {
            UserSelectVehicleTopAppBar(nav)
        }) { innerPadding ->
        Box(modifier = Modifier.fillMaxSize().padding(innerPadding)) {
            NavHost(
                modifier = Modifier,
                navController = bottomNavController,
                startDestination = UserClanOverviewRoute.SelectVehicle
            ) {
                composable<UserClanOverviewRoute.SelectVehicle>{
                    val viewModel: UserSelectVehicleViewModel = koinViewModel()

                    UserSelectVehicleScreen(
                        nav = bottomNavController,
                        event = viewModel.onEvent,
                        state = viewModel.state.collectAsStateWithLifecycle().value)
                }
                composable<UserClanOverviewRoute.StartOverview>{
                    val viewModel: UserStartOverviewViewModel = koinViewModel()

                    StartOverviewScreen(
                        nav = nav,
                        event = viewModel.onEvent,
                        state = viewModel.state.collectAsStateWithLifecycle().value
                    )
                }

            }
        }
    }
}