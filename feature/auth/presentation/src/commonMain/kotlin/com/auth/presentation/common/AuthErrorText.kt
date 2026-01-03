package com.auth.presentation.common

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.auth.presentation.mappers.toMessageRes
import com.kmp.auth.api.model.AuthError
import org.jetbrains.compose.resources.stringResource

@Composable
fun AuthErrorText(
    error: AuthError?,
    modifier: Modifier = Modifier
) {
    if (error == null) return

    val messageRes = remember(error) {
        error.toMessageRes()
    }

    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.spacedBy(4.dp)
    ) {
        Text(
            text = stringResource(messageRes),
            color = MaterialTheme.colorScheme.error,
            style = MaterialTheme.typography.bodySmall
        )

        // Optional: show backend reason if available
        when (error) {
            is AuthError.AuthMultiFactor ->
                error.reason?.let {
                    Text(
                        text = it,
                        color = MaterialTheme.colorScheme.error,
                        style = MaterialTheme.typography.bodySmall
                    )
                }

            is AuthError.Unknown ->
                error.reason?.let {
                    Text(
                        text = it,
                        color = MaterialTheme.colorScheme.error,
                        style = MaterialTheme.typography.bodySmall
                    )
                }

            else -> Unit
        }
    }
}