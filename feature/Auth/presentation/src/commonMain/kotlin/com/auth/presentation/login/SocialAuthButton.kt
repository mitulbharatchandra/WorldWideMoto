package com.auth.presentation.login

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.clickable
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Phone
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.ui.tooling.preview.Preview
import worldwidemoto.feature.auth.presentation.generated.resources.Res
import worldwidemoto.feature.auth.presentation.generated.resources.apple_logo
import worldwidemoto.feature.auth.presentation.generated.resources.google_logo

@Composable
fun SocialAuthButton(
    modifier: Modifier = Modifier,
    backgroundColor: Color,
    contentColor: Color,
    borderColor: Color? = null,
    text: String,
    onClick: () -> Unit,
    icon: @Composable () -> Unit
) {
    Surface(
        modifier = modifier
            .height(48.dp)
            .fillMaxWidth(),
        shape = RoundedCornerShape(50),
        color = backgroundColor,
        border = borderColor?.let { BorderStroke(1.dp, it) },
        onClick = { }
    ) {
        Row(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 16.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center
        ) {
            icon()
            Spacer(modifier = Modifier.width(10.dp))
            Text(
                text = text,
                color = contentColor,
                style = MaterialTheme.typography.labelLarge
            )
        }
    }
}

@Composable
fun GoogleSignInButton(
    modifier: Modifier = Modifier,
    onClick: () -> Unit
) {
    val isDark = isSystemInDarkTheme()

    val backgroundColor = if (isDark) {
        Color(0xFF1F1F1F) // Google dark button background
    } else {
        Color.White
    }

    val contentColor = if (isDark) {
        Color.White
    } else {
        Color(0xFF1F1F1F)
    }

    val borderColor = if (isDark) {
        Color.White
    } else {
        Color(0xFFDDDDDD)
    }
    SocialAuthButton(
        modifier = modifier,
        backgroundColor = backgroundColor,
        contentColor = contentColor,
        borderColor = borderColor,
         // official Google G
        text = "Sign in with Google",
        onClick = onClick
    ) {
        Icon(
            painter = painterResource(Res.drawable.google_logo),
            contentDescription = "Sign in with Google",
            tint = Color.Unspecified
        )
    }
}

@Composable
fun AppleSignInButton(
    modifier: Modifier = Modifier,
    onClick: () -> Unit,
    isDark: Boolean = isSystemInDarkTheme()
) {
    val backgroundColor = if (isDark) {
        Color.White
    } else {
        Color.Black
    }

    val contentColor = if (isDark) {
        Color.Black
    } else {
        Color.White
    }

    SocialAuthButton(
        modifier = modifier,
        backgroundColor = backgroundColor,
        contentColor = contentColor,
        text = "Sign in with Apple",
        onClick = onClick
    ) {
        Icon(
            painter = painterResource(Res.drawable.apple_logo),
            contentDescription = "Sign in with Apple",
            tint = Color.Unspecified
        )
    }
}

@Composable
fun PhoneSignInButton(
    modifier: Modifier = Modifier,
    onClick: () -> Unit
) {
    val isDark = isSystemInDarkTheme()

    val backgroundColor = if (isDark) {
        Color(0xFF1F1F1F) // Google dark button background
    } else {
        Color.White
    }

    val contentColor = if (isDark) {
        Color.White
    } else {
        Color(0xFF1F1F1F)
    }

    val borderColor = if (isDark) {
        Color.White
    } else {
        Color(0xFFDDDDDD)
    }
    SocialAuthButton(
        modifier = modifier,
        backgroundColor = backgroundColor,
        contentColor = contentColor,
        borderColor = borderColor,
        text = "Sign in with phone",
        onClick = onClick
    ) {
        Icon(
            imageVector = Icons.Filled.Phone,
            contentDescription = "Sign in with phone",
            tint = contentColor
        )
    }
}

@Composable
@Preview
fun GoogleSignInButtonPreview() {
    GoogleSignInButton(onClick = {})
}

@Composable
@Preview
fun AppleSignInButtonPreview() {
    AppleSignInButton(onClick = {})
}

@Composable
@Preview
fun PhoneSignInButtonPreview() {
    PhoneSignInButton(onClick = {})
}