package com.worldwidemoto.firebase.auth

import com.google.firebase.FirebaseNetworkException
import com.google.firebase.auth.FirebaseAuthException
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException
import com.google.firebase.auth.FirebaseAuthInvalidUserException
import com.kmp.auth.api.model.AuthError

internal fun Throwable.toAuthError(): AuthError =
    when (this) {
        is FirebaseAuthInvalidCredentialsException -> {
            when (errorCode) {
                "ERROR_INVALID_EMAIL" -> AuthError.InvalidEmail
                "ERROR_WRONG_PASSWORD" -> AuthError.WrongPassword
                "ERROR_WEAK_PASSWORD" -> AuthError.WeakPassword
                else -> AuthError.InvalidEmail
            }
        }

        is FirebaseAuthInvalidUserException -> {
            when (errorCode) {
                "ERROR_USER_NOT_FOUND" -> AuthError.UserNotFound
                else -> AuthError.UserNotFound
            }
        }

        is FirebaseAuthException -> {
            when (errorCode) {
                "ERROR_EMAIL_ALREADY_IN_USE" -> AuthError.EmailAlreadyInUse
                "ERROR_TOO_MANY_REQUESTS" -> AuthError.TooManyRequests
                else -> AuthError.Unknown(errorCode)
            }
        }

        is FirebaseNetworkException ->
            AuthError.NetworkError

        else ->
            AuthError.Unknown(message)
    }
