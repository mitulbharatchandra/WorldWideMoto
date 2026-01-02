package com.auth.presentation.common

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.auth.domain.model.PasswordValidationError
import com.auth.domain.model.PasswordValidationState
import org.jetbrains.compose.resources.StringResource
import org.jetbrains.compose.resources.stringResource
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

@Composable
fun PasswordErrorText(
    error: PasswordValidationError?,
    modifier: Modifier = Modifier
) {
    val messages = remember(error) {
        when (error) {
            is PasswordValidationError.InvalidPassword ->
                error.passwordValidationState
                    ?.toErrorMessages()
                    .orEmpty()
            else -> emptyList()
        }
    }

    if (messages.isEmpty()) return

    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.spacedBy(4.dp)
    ) {
        messages.forEach { messageRes ->
            Text(
                text = stringResource(messageRes),
                color = MaterialTheme.colorScheme.error,
                style = MaterialTheme.typography.bodySmall
            )
        }
    }
}
