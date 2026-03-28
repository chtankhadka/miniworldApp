package com.chtan.miniworld.presentation.user.drive

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.chtan.miniworld.data.datasource.network.websockets.WebSocketEvent
import com.chtan.miniworld.domain.repository.SocketRepository
import com.chtan.miniworld.domain.repository.UserRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch


class DriveViewModel(
    val repository: UserRepository,
    val socketRepository: SocketRepository
) : ViewModel() {

    val messages = repository.messages
    val connected = repository.connected

    // New: Live image bytes
    val latestFrameBytes = socketRepository.latestFrameBytes

    private val _state = MutableStateFlow(DriveState())
    val state: StateFlow<DriveState> = _state

    init {
        connectToCar()
//        connectToCam(true)

    }

    val onEvent: (DriveEvent) -> Unit = { event ->
        viewModelScope.launch {
            when (event) {
//               event is DriveEvent.OnAccelerator -> repository.sendMessage(state.value.driveControlDto.copy())
//                is DriveEvent.OnTurning -> repository.sendMessage(state.value.driveControlDto.copy())
//                DriveEvent.OnClickBreak -> {
//                    repository.sendMessage(state.value.driveControlDto.copy())
//                }
//                is DriveEvent.OnGearChange -> {
//                    repository.sendMessage(state.value.driveControlDto.copy(g = event.value))
//                }
//
//                is DriveEvent.OnDrivingModeChange -> {
//                    repository.sendMessage(state.value.driveControlDto.copy())
//                }

                DriveEvent.ConnectToCar -> {
                    connectToCar()
                }

                is DriveEvent.OnChangeAccelerator -> {
                    val newValue = state.value.driveControlDto.copy(t =   event.value * 28, b = false)
                    if (newValue.t != state.value.driveControlDto.t * 28) {
                        repository.sendMessage(newValue)
                        _state.update {
                            it.copy(driveControlDto = newValue.copy(t = event.value, b = false))
                        }
                        println("change value"+ state.value.driveControlDto)
                    }

                }
                is DriveEvent.OnChangeBreak -> {
                    val newValue = state.value.driveControlDto.copy(b =   event.value, t = 0)
                    if (newValue == state.value.driveControlDto) return@launch

                    repository.sendMessage(newValue)
                    _state.update {
                        it.copy(driveControlDto = newValue.copy(b = event.value))

                    }
                    println("change value"+ state.value.driveControlDto)

                }
                is DriveEvent.OnChangeGear -> {
                    val newValue = state.value.driveControlDto.copy(g =   event.value)
                    if (newValue == state.value.driveControlDto) return@launch

                    repository.sendMessage(newValue)
                    _state.update {
                        it.copy(driveControlDto = newValue.copy(g = event.value))

                    }
                    println("change value"+ state.value.driveControlDto)

                }
                is DriveEvent.OnChangeMode -> {
                    val newValue = state.value.driveControlDto.copy(m =   event.value)
                    if (newValue == state.value.driveControlDto) return@launch

                    repository.sendMessage(newValue)
                    _state.update {
                        it.copy(driveControlDto = newValue.copy(m = event.value))

                    }
                    println("change value"+ state.value.driveControlDto)

                }
                is DriveEvent.OnChangeSteering -> {
                    val newValue = state.value.driveControlDto.copy(s = event.value)

                    repository.sendMessage(newValue.copy(t = state.value.driveControlDto.t * 28))
                    _state.update {
                        it.copy(driveControlDto = newValue.copy(s = event.value))

                    }
                    println("change value"+ state.value.driveControlDto)

                }

                is DriveEvent.ConnectToCam -> {
                    connectToCam(event.value)

                }

                is DriveEvent.OnXYChange -> {
                    val newValue = state.value.driveControlDto.copy(isXY = true, cx = event.x, cy = event.y)
                    if (newValue == state.value.driveControlDto) return@launch

                    repository.sendMessage(newValue)
                    _state.update {
                        it.copy(driveControlDto = newValue.copy(isXY = event.isXYChange))

                    }
                    println("change value"+ state.value.driveControlDto)
                }
            }
        }
    }

    fun connectToCar() {
        viewModelScope.launch {
            repository.connectDriveWebSocket()
            repository.events.collect { event ->
                when (event) {
                    is WebSocketEvent.Connected -> {
                        _state.update {
                            it.copy(
                                isConnected = true
                            )
                        }
                    }

                    is WebSocketEvent.Closed ->{
                        _state.update {
                            it.copy(
                                isConnected = false
                            )
                        }
                        println("❌ Closed: ${event.reason}")
                    }
                    is WebSocketEvent.Error -> {
                        _state.update {
                            it.copy(
                                isConnected = false
                            )
                        }
                        println("⚠️ Error: ${event.message}")
                    }
                }
            }

        }
    }
    fun connectToCam(connect: Boolean) {
            viewModelScope.launch(Dispatchers.Main) {
                socketRepository.connectImageWebSocket()
                socketRepository.events.collect { event ->
                    when (event) {
                        is WebSocketEvent.Connected -> {
                            _state.update {
                                it.copy(
                                    isCamConnected = true
                                )
                            }
                        }

                        is WebSocketEvent.Closed ->{
                            _state.update {
                                it.copy(
                                    isConnected = false
                                )
                            }
                            println("❌ Closed: ${event.reason}")
                        }
                        is WebSocketEvent.Error -> {
                            _state.update {
                                it.copy(
                                    isConnected = false
                                )
                            }
                            println("⚠️ Error: ${event.message}")
                        }
                    }
                }

            }
    }
}
