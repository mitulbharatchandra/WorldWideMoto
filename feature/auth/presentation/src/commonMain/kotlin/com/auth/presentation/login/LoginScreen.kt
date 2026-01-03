@file:OptIn(ExperimentalMaterial3Api::class)

package com.auth.presentation.login

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.auth.presentation.common.AuthErrorText
import com.auth.presentation.common.PasswordErrorText
import com.core.presentation.util.ObserveAsEvents
import com.kmp.designsystem.theme.AppTheme
import org.jetbrains.compose.resources.stringResource
import org.jetbrains.compose.ui.tooling.preview.Preview
import org.koin.compose.viewmodel.koinViewModel
import worldwidemoto.feature.auth.presentation.generated.resources.Res
import worldwidemoto.feature.auth.presentation.generated.resources.welcome_to_world_wide_auto


@Composable
fun LoginRoot(
    viewModel: LoginViewModel = koinViewModel(),
    onLoginSuccess: () -> Unit,
    onForgotPasswordClick: (String?) -> Unit,
    onSigInWithPhoneClick: () -> Unit
) {
    val state by viewModel.state.collectAsStateWithLifecycle()

    ObserveAsEvents(viewModel.events) { event ->
        when(event) {
            LoginEvent.LoginSuccess -> onLoginSuccess()
            LoginEvent.SignupSuccess -> onLoginSuccess()
        }
    }

    LoginScreen(
        uiState = state,
        onAction = { action ->
            when (action) {
                is LoginAction.OnForgotPasswordClick -> onForgotPasswordClick(action.email)
                LoginAction.OnSigInWithPhoneClick -> onSigInWithPhoneClick()
                else -> Unit
            }
            viewModel.onAction(action)
        }
    )
}
@Composable
fun LoginScreen(
    uiState: LoginState,
    onAction: (LoginAction) -> Unit,
) {
    Surface {
        val scrollState = rememberScrollState()
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp)
                .verticalScroll(scrollState),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {

            if (uiState.isLoading) {
                CircularProgressIndicator()
            }

            Text(
                text = stringResource(Res.string.welcome_to_world_wide_auto),
                style = MaterialTheme.typography.headlineMedium
            )
            Text(
                text = "Sign in or create an account to get started.",
                style = MaterialTheme.typography.bodyMedium,
                modifier = Modifier.padding(top = 8.dp, bottom = 32.dp)
            )

            Spacer(modifier = Modifier.height(32.dp))

            AuthErrorText(
                error = uiState.authError,
                modifier = Modifier.fillMaxWidth()
            )
            Spacer(modifier = Modifier.height(8.dp))
            var email by remember { mutableStateOf("") }
            OutlinedTextField(
                value = email,
                onValueChange = {
                    email = it
                    onAction(LoginAction.EmailChanged(it))
                },
                label = { Text("Email address") },
                leadingIcon = { Icon(Icons.Filled.Email, contentDescription = "Email Icon") },
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email),
                modifier = Modifier.fillMaxWidth()
            )

            Spacer(modifier = Modifier.height(16.dp))

            var password by remember { mutableStateOf("") }
            OutlinedTextField(
                value = password,
                onValueChange = {
                    password = it
                    onAction(LoginAction.PasswordChanged(it))
                },
                label = { Text("Password") },
                leadingIcon = { Icon(Icons.Filled.Lock, contentDescription = "Password Icon") },
                visualTransformation = PasswordVisualTransformation(),
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
                modifier = Modifier.fillMaxWidth(),
                supportingText = {
                    PasswordErrorText(
                        error = uiState.passwordValidationError
                    )
                }
            )

            Text(
                text = "Forgot Password?",
                modifier = Modifier
                    .fillMaxWidth()
                    .clickable (
                        onClick = {
                            onAction(LoginAction.OnForgotPasswordClick(email = email))
                        },
                        enabled = !uiState.isLoading
                    )
                    .padding(top = 16.dp),
                textAlign = TextAlign.End,
                style = MaterialTheme.typography.bodyMedium
            )

            Spacer(modifier = Modifier.height(16.dp))

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceEvenly
            ) {
                Button(
                    onClick = {
                        onAction(
                            LoginAction.SignupWithEmail(
                                email = email,
                                password = password
                            )
                        )
                    },
                    shape = RoundedCornerShape(50),
                    modifier = Modifier.weight(1f).padding(end = 8.dp),
                    enabled = uiState.canSubmit
                ) {
                    Text("Sign Up")
                }
                Button(
                    onClick = {
                        onAction(
                            LoginAction.LoginWithEmail(
                                email = email,
                                password = password
                            )
                        )
                    },
                    shape = RoundedCornerShape(50),
                    colors = ButtonDefaults.buttonColors(containerColor = MaterialTheme.colorScheme.primary),
                    modifier = Modifier.weight(1f).padding(start = 8.dp),
                    enabled = uiState.canSubmit
                ) {
                    Text("Sign In")
                }
            }

            Spacer(modifier = Modifier.height(32.dp))

            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                HorizontalDivider(modifier = Modifier.weight(1f))
                Text(
                    text = "OR",
                    modifier = Modifier.padding(horizontal = 8.dp),
                    fontSize = 14.sp
                )
                HorizontalDivider(modifier = Modifier.weight(1f))
            }

            Spacer(modifier = Modifier.height(16.dp))

            AppleSignInButton(
                onClick = {}
            )

            Spacer(modifier = Modifier.height(16.dp))

            GoogleSignInButton(
                onClick = {}
            )

            Spacer(modifier = Modifier.height(16.dp))

            PhoneSignInButton(onClick = { onAction(LoginAction.OnSigInWithPhoneClick) })

            Spacer(modifier = Modifier.height(16.dp))

            Text(
                text = "By continuing, you agree to our Terms and Privacy Policy.",
                style = MaterialTheme.typography.bodySmall,
                textAlign = TextAlign.Center
            )
        }
    }
}

@Preview
@Composable
fun LoginScreenPreviewLight() {
    AppTheme {
        LoginScreen(
            uiState = LoginState(),
            onAction = {}
        )
    }
}

@Preview
@Composable
fun LoginScreenPreviewDark() {
    AppTheme(darkTheme = true) {
        LoginScreen(
            uiState = LoginState(),
            onAction = {}
        )
    }
}
