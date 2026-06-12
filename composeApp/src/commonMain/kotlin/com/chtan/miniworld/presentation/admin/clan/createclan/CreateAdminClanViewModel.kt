package com.chtan.miniworld.presentation.admin.clan.createclan

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.chtan.miniworld.data.datasource.network.model.admin.clan.CreateClanRequest
import com.chtan.miniworld.domain.repository.AdminRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import com.chtan.miniworld.data.datasource.network.result.RemoteResult

class CreateAdminClanViewModel(
    val repository: AdminRepository
): ViewModel() {


    private val _state = MutableStateFlow(CreateAdminClanState())
    val state: StateFlow<CreateAdminClanState> = _state

    init {

    }

    val onEvent: (CreateAdminClanEvent) -> Unit = { event ->
        viewModelScope.launch {


            when (event) {
                is CreateAdminClanEvent.CreateClan -> {
                    createClan(event)
                }
                is CreateAdminClanEvent.DismissError -> {
                    _state.update { it.copy(error = null) }
                }
            }
        }
    }


    private suspend fun  createClan(event: CreateAdminClanEvent.CreateClan) {
        val request = repository.createClan(
            data = CreateClanRequest(
                name = event.name,
                tag = event.tag,
                description = event.details
            )
        )

        when(request){
            is RemoteResult.Error -> {
                _state.update {
                    it.copy(
                        error = request.error
                    )
                }
            }
            is RemoteResult.Success -> {
                    _state.update {
                        it.copy(
                            isClanCreated = true,
                            error = null
                        )
                    }

                }
            }

    }
}
