package com.chtan.miniworld.presentation.user.dashboard

import androidx.lifecycle.ViewModel
import com.chtan.miniworld.domain.repository.UserRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class UserDashboardViewModel(
    val repository: UserRepository,
) : ViewModel() {
    private val _state = MutableStateFlow(UserDashboardState())
    val state: StateFlow<UserDashboardState> = _state

    init {

    }

    val onEvent: (UserDashboardEvent) -> Unit = { event ->
    }
}