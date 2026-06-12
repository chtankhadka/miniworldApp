package com.chtan.miniworld.presentation.user.clanOverview

import androidx.lifecycle.ViewModel
import com.chtan.miniworld.domain.repository.UserRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class UserClanOverviewViewModel (
    val repository: UserRepository,
) : ViewModel() {
    private val _state = MutableStateFlow(UserClanOverviewState())
    val state: StateFlow<UserClanOverviewState> = _state

    init {

    }

    val onEvent: (UserClanOverviewEvent) -> Unit = { event ->
    }
}