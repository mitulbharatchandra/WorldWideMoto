package com.auth.presentation.login

sealed interface LoginAction {
    data class LoginWithEmail(
        val email: String,
        val password: String
    ) : LoginAction
    data class SignupWithEmail(
        val email: String,
        val password: String
    ) : LoginAction

    data class OnForgotPasswordClick(
        val email: String? = null
    ) : LoginAction
}