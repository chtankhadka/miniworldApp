package com.chtan.miniworld.presentation.user.selectVehicle

import androidx.lifecycle.ViewModel
import com.chtan.miniworld.domain.repository.UserRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class UserSelectVehicleViewModel(
    val repository: UserRepository,
) : ViewModel() {
    private val _state = MutableStateFlow(UserSelectVehicleState())
    val state: StateFlow<UserSelectVehicleState> = _state

    init {

    }

    val onEvent: (UserSelectVehicleEvent) -> Unit = { event ->
    }

}