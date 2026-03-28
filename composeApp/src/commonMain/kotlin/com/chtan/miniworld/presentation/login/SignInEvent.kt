package com.chtan.miniworld.presentation.login

sealed interface SignInEvent {
    data class SignInAction(val userMail: String, val password: String): SignInEvent
}