package com.auth.presentation.mappers

import com.auth.domain.model.PasswordValidationState
import org.jetbrains.compose.resources.StringResource
import worldwidemoto.feature.auth.presentation.generated.resources.Res
import worldwidemoto.feature.auth.presentation.generated.resources.password_must_be_at_least_9_characters
import worldwidemoto.feature.auth.presentation.generated.resources.password_must_contain_at_least_one_lowercase_letter
import worldwidemoto.feature.auth.presentation.generated.resources.password_must_contain_at_least_one_number
import worldwidemoto.feature.auth.presentation.generated.resources.password_must_contain_at_least_one_uppercase_letter

fun PasswordValidationState.toErrorMessages(): List<StringResource> {
    val errors = mutableListOf<StringResource>()

    if (!hasLowerCaseCharacter) {
        errors += Res.string.password_must_contain_at_least_one_lowercase_letter
    }
    if (!hasUpperCaseCharacter) {
        errors += Res.string.password_must_contain_at_least_one_uppercase_letter
    }
    if (!hasNumber) {
        errors += Res.string.password_must_contain_at_least_one_number
    }
    if (!hasMinLength) {
        errors += Res.string.password_must_be_at_least_9_characters
    }

    return errors
}