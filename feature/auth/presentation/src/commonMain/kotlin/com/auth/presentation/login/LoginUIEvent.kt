package com.auth.presentation.login

sealed interface LoginUIEvent {
    data class LoginWithEmail(
        val email: String,
        val password: String
    ) : LoginUIEvent
    data class signupWithEmail(
        val email: String,
        val password: String
    ) : LoginUIEvent
}