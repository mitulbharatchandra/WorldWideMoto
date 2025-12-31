package com.auth.domain.model

data class PhoneNumberValidationState(
    val isNotBlank: Boolean,
    val hasValidLength: Boolean,
    val hasOnlyDigits: Boolean,
    val hasValidCountryCode: Boolean
) {
    val isValid: Boolean
        get() = isNotBlank &&
                hasValidLength &&
                hasOnlyDigits &&
                hasValidCountryCode
}