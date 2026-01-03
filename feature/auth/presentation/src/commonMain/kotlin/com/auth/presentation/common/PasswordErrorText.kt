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
import com.auth.presentation.mappers.toErrorMessages
import org.jetbrains.compose.resources.stringResource

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
