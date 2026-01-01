package com.auth.domain.model

import kotlin.jvm.JvmInline

@JvmInline
value class PhoneNumber private constructor(
    val value: String
) {

    companion object {

        /**
         * E.164 basic pattern:
         * +[country code][subscriber number]
         * Example: +14155552671
         */
        private const val E164_REGEX =
            "^\\+[1-9]\\d{7,14}$"

        fun validate(phoneNumber: String): PhoneNumberValidationState {
            val trimmed = phoneNumber.trim()

            val isNotBlank = trimmed.isNotEmpty()
            val hasOnlyDigits =
                trimmed.removePrefix("+").all { it.isDigit() }

            val hasValidLength =
                trimmed.length in 9..16 // + + digits

            val hasValidCountryCode =
                trimmed.startsWith("+") &&
                        trimmed.length > 1 &&
                        trimmed[1].isDigit()

            return PhoneNumberValidationState(
                isNotBlank = isNotBlank,
                hasOnlyDigits = hasOnlyDigits,
                hasValidLength = hasValidLength,
                hasValidCountryCode = hasValidCountryCode
            )
        }

        fun create(phoneNumber: String): PhoneNumber {
            val validation = validate(phoneNumber)

            require(validation.isValid) {
                "Invalid phone number format"
            }

            // Normalize (E.164 compliant)
            return PhoneNumber(phoneNumber.trim())
        }
    }
}