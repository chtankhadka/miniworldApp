package com.chtan.miniworld

import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.*
import androidx.navigation.compose.rememberNavController
import com.chtan.miniworld.domain.repository.UserRepository
import com.chtan.miniworld.presentation.user.drive.DriveScreen
import org.jetbrains.compose.ui.tooling.preview.Preview
import org.koin.compose.koinInject
import org.koin.compose.viewmodel.koinViewModel

@Composable
@Preview
fun App() {

        val scope = rememberCoroutineScope()
        val mainViewModel = koinViewModel<MainViewModel>()
        val navController = rememberNavController()
        var route by remember { mutableStateOf<Route?>(Route.Derive) }
        var check = koinInject<UserRepository>()
        MaterialTheme {
//            mainViewModel.getMyProfile { bool ->
//                if (bool) {
//                    route = Route.Dashboard
//                    mainViewModel.StartSocket()
//                } else {
//                    route = Route.SignIn
//                }
//            }
            route?.let {
                Navigation(
                    scope = scope,
                    mainViewModel = mainViewModel,
                    navController = navController,
                    onBack = {},
                    startDestination = it
                )
            }



    }
}