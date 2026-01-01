package com.auth.domain.model

data class PasswordValidationState(
    val hasMinLength: Boolean,
    val hasNumber: Boolean,
    val hasLowerCaseCharacter: Boolean,
    val hasUpperCaseCharacter: Boolean
) {
    val isValid: Boolean
        get() = hasMinLength &&
                hasNumber &&
                hasLowerCaseCharacter &&
                hasUpperCaseCharacter
}