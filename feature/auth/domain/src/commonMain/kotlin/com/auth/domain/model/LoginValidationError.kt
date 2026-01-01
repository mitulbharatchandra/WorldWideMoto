package com.auth.domain.model

sealed class EmailValidationError : Throwable() {
    object EmptyEmail : EmailValidationError()
    object InvalidEmail : EmailValidationError()
}

sealed class PasswordValidationError : Throwable() {
    data class InvalidPassword(val passwordValidationState: PasswordValidationState?) : PasswordValidationError()
}