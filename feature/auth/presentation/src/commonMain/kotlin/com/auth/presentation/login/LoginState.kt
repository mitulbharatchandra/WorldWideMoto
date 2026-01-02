package com.auth.presentation.login

import com.auth.domain.model.EmailValidationError
import com.auth.domain.model.PasswordValidationError
import com.kmp.auth.api.model.AuthError

data class LoginState(
    val emailText: String = "",
    val passwordText: String = "",
    val isLoading: Boolean = false,
    val emailValidationError: EmailValidationError? = null,
    val passwordValidationError: PasswordValidationError? = null,
    val authError: AuthError? = null,
    val throwable: Throwable? = null
) {
    // 🔹 Button enabled only if both are valid and not loading
    val canSubmit: Boolean
        get() =
            emailValidationError == null &&
            passwordValidationError == null &&
            emailText.isNotBlank() &&
            passwordText.isNotBlank() &&
            !isLoading
}