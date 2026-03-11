package com.chtan.miniworld

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.chtan.miniworld.presentation.user.drive.DriveScreen
import com.chtan.miniworld.presentation.user.drive.DriveViewModel
import com.chtan.miniworld.presentation.user.login.SignInScreen
import com.chtan.miniworld.presentation.user.login.SignInViewModel
import kotlinx.coroutines.CoroutineScope

import org.koin.compose.viewmodel.koinViewModel

@Composable
fun Navigation(
    navController: NavHostController,
    onBack: () -> Unit,
    startDestination: Route,
    mainViewModel: MainViewModel,
    scope: CoroutineScope
){
    NavHost(
        navController = navController,
        startDestination = startDestination
    ) {
        composable<Route.SignIn> {
            val viewModel = koinViewModel<SignInViewModel>()
            SignInScreen(
                nav = navController,
                events = viewModel.onEvent,
                states = viewModel.state.collectAsState().value
            )

        }

        composable<Route.Derive> {
            val viewModel: DriveViewModel = koinViewModel()
            DriveScreen(
                nav = navController,
                event = viewModel.onEvent,
                state = viewModel.state.collectAsStateWithLifecycle().value,
                viewModel = viewModel
            )
        }
//        composable<Route.Signup> {
//            val viewModel = koinViewModel<SignupViewModel>()
//
//            SignupScreen(
//                events = viewModel.onEvent,
//                viewModel = viewModel,
//
//            )
//
//        }
//
//        composable<Route.Chat> {
//            val viewModel = koinViewModel<ChatViewModel>()
//            ChatScreen(
//                nav = navController,
//                states = viewModel.state.collectAsState().value,
//                events = viewModel.onEvents
//            )
//        }
//
//        composable<Route.Dashboard> {
//            val viewModel = koinViewModel<DashboardViewModel>()
//
//            DashboardScreen(
//                nav = navController,
//                states = viewModel.state.collectAsState().value,
//                events = viewModel.onEvents
//            )
//        }
//        composable<Route.Message> {
//            val viewModel = koinViewModel<MessageViewModel>()
//            MessageScreen(
//                nav = navController,
//                states = viewModel.state.collectAsState().value,
//                events = viewModel.onEvents
//            )
//        }

    }
}