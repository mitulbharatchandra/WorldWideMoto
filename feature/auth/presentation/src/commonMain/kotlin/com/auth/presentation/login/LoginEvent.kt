package com.auth.presentation.login

sealed interface LoginEvent {
    data object LoginSuccess: LoginEvent
    data object SignupSuccess: LoginEvent
}