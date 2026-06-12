package com.chtan.miniworld.presentation.user.clanOverview.startOverview

import androidx.lifecycle.ViewModel
import com.chtan.miniworld.domain.repository.UserRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class UserStartOverviewViewModel(
    val repository: UserRepository
): ViewModel() {
    private val _state = MutableStateFlow(UserStartOverviewState())
    val state: StateFlow<UserStartOverviewState> = _state

    init {

    }

    val onEvent: (UserStartOverviewEvent) -> Unit = { event ->
    }
}