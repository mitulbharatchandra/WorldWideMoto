package com.kmp.auth.api.model

sealed class AuthCredentials {
    object Anonymous : AuthCredentials()
    data class EmailPassword(
        val email: String,
        val password: String
    ) : AuthCredentials()
}