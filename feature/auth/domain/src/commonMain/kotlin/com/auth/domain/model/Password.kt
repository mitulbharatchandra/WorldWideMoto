package com.auth.domain.model

import kotlin.jvm.JvmInline

@JvmInline
value class Password private constructor(
    val value: String
) {

    companion object {

        const val MIN_PASSWORD_LENGTH = 9

        fun validate(password: String): PasswordValidationState {
            val hasMinLength = password.length >= MIN_PASSWORD_LENGTH
            val hasNumber = password.any { it.isDigit() }
            val hasLowerCaseCharacter = password.any { it.isLowerCase() }
            val hasUpperCaseCharacter = password.any { it.isUpperCase() }

            return PasswordValidationState(
                hasMinLength = hasMinLength,
                hasNumber = hasNumber,
                hasLowerCaseCharacter = hasLowerCaseCharacter,
                hasUpperCaseCharacter = hasUpperCaseCharacter
            )
        }

        fun create(password: String): Password {
            val validationState = validate(password)
            require(validationState.isValid) {
                throw PasswordValidationError.InvalidPassword(validationState)
            }
            return Password(password)
        }
    }
}
