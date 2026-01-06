package com.apple.signin

import androidx.compose.runtime.Composable
import com.apple.signin.model.AppleSignInResult

@Composable
expect fun AppleSignInButton(
    onResult: (AppleSignInResult) -> Unit,
    content: @Composable (onClick: () -> Unit) -> Unit
)