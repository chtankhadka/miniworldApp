package com.chtan.miniworld.presentation.user.login

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.chtan.miniworld.domain.repository.UserRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import org.chtan.personalwork.core.domain.Result
import com.chtan.miniworld.data.datasource.network.model.authorization.SignInRequestModel

class SignInViewModel(
    private val userRepository: UserRepository,
    private val preferences: DataStore<Preferences>
) : ViewModel() {
    private val _state = MutableStateFlow(SignInState())
    val state: StateFlow<SignInState> = _state

    init {


    }

    val onEvent: (event: SignInEvent) -> Unit = { event ->
        viewModelScope.launch(Dispatchers.IO) {
            when (event) {
                is SignInEvent.SignInAction -> {
                    val signInRequest = userRepository.signIn(
                        data = SignInRequestModel(
                            userMail = event.userMail, password = event.password
                        )
                    )
                    when (signInRequest) {
                        is Result.Error -> {
                            println("I am error ...............................")
                        }

                        is Result.Success -> {
                            preferences.edit { dataStore ->
                                dataStore[stringPreferencesKey("access_token")] = signInRequest.data.data.accessToken
                                dataStore[stringPreferencesKey("id")] = signInRequest.data.data.userId
                                dataStore[stringPreferencesKey("refresh_token")] = signInRequest.data.data.refreshToken
                            }
                            _state.update {
                                it.copy(
                                    isLoggedIn = true
                                )
                            }
                        }
                    }

                }
            }
        }
    }
}