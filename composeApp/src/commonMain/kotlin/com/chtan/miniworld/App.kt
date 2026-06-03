package com.chtan.miniworld

import androidx.compose.runtime.*
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.rememberNavController
import com.chtan.miniworld.domain.repository.UserRepository
import com.chtan.miniworld.presentation.theme.MiniWorldTheme
import org.koin.compose.koinInject
import org.koin.compose.viewmodel.koinViewModel

@Composable
@Preview
fun App() {

        val scope = rememberCoroutineScope()
        val mainViewModel = koinViewModel<MainViewModel>()
        val navController = rememberNavController()
        var route by remember { mutableStateOf<Route?>(Route.UserDashboard) }
        var check = koinInject<UserRepository>()
    MiniWorldTheme {
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