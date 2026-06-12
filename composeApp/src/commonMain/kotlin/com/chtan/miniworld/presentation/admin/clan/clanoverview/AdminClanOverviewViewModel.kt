package com.chtan.miniworld.presentation.admin.clan.clanoverview

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.chtan.miniworld.data.datasource.network.model.admin.clan.GetCreatedClansResponseModel
import com.chtan.miniworld.data.datasource.network.result.RemoteResult
import com.chtan.miniworld.domain.repository.AdminRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class AdminClanOverviewViewModel(
    val repository: AdminRepository
): ViewModel() {

    private val _state = MutableStateFlow(AdminClanOverviewState())
    val state: StateFlow<AdminClanOverviewState> = _state


    init {
        getCreatedClans()
    }

    val onEvent: (AdminClanOverviewEvent) -> Unit = { event ->
        when(event){
            is AdminClanOverviewEvent.AddDeviceInClan -> {
                addDeviceInClan(event)

        }

            AdminClanOverviewEvent.DismissError -> {
                _state.update { it.copy(error = null) }

            }
        }

    }

    private fun getCreatedClans(){
        viewModelScope.launch {
            when(val request = repository.getCreatedClans()){
                is RemoteResult.Error -> {
                    _state.update {
                        it.copy(
                            error = request.error
                        )
                    }
                }
                is RemoteResult.Success<GetCreatedClansResponseModel> -> {
                    val clans = request.data.data ?: emptyList()
                    print("list is here$clans")
                    _state.update {
                        it.copy(
                            clanList = clans,
                            error = null
                        )
                    }

                }
            }
        }
    }
    private fun addDeviceInClan(event: AdminClanOverviewEvent.AddDeviceInClan) {
        viewModelScope.launch {
            when(val request = repository.addDeviceInClan(event.deviceDetails)){
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

                            error = null
                        )
                    }

        }


        }

        }
    }

}
