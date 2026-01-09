package com.apple.signin

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import com.apple.signin.model.AppleSignInResult

@Composable
actual fun AppleSignInButton(
    onResult: (AppleSignInResult) -> Unit,
    content: @Composable ((onClick: () -> Unit) -> Unit)
) {
    val onClick = remember {
        {

        }
    }
    content(onClick)
}