package com.auth.domain.model

import kotlin.jvm.JvmInline

@JvmInline
value class Email private constructor(
    val value: String
) {

    companion object {

        private const val EMAIL_PATTERN =
            "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$"

        fun create(email: String): Email {
            if (email.isBlank()) {
                throw EmailValidationError.EmptyEmail
            }
            if (!EMAIL_PATTERN.toRegex().matches(email)) {
                throw EmailValidationError.InvalidEmail
            }
            return Email(email)
        }
    }
}