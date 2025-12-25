package com.kmp.auth.api.model

sealed class AuthError : Throwable() {
    object InvalidEmail : AuthError()
    object WrongPassword : AuthError()
    object UserNotFound : AuthError()
    object EmailAlreadyInUse : AuthError()
    object WeakPassword : AuthError()
    object NetworkError : AuthError()
    object TooManyRequests : AuthError()
    object InvalidActionCode : AuthError()
    object RecentLoginRequired: AuthError()
    object InvalidCredentials: AuthError()
    data class AuthMultiFactor(
        val reason: String? = null
    ): AuthError()
    data class Unknown(
        val reason: String? = null
    ) : AuthError()
}
