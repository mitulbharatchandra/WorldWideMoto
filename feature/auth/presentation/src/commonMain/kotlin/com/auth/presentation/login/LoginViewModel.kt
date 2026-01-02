package com.auth.presentation.login

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.auth.domain.model.EmailValidationError
import com.auth.domain.model.PasswordValidationError
import com.auth.domain.usecases.GetCurrentUser
import com.auth.domain.usecases.LoginWithEmail
import com.auth.domain.usecases.Signup
import com.kmp.auth.api.model.AuthError
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class LoginViewModel(
    private val loginWithEmail: LoginWithEmail,
    private val signUp: Signup,
    private val getCurrentUser: GetCurrentUser,
): ViewModel() {

    private val _state = MutableStateFlow(LoginState())
    val state = _state.asStateFlow()

    private val eventChannel = Channel<LoginEvent>()
    val events = eventChannel.receiveAsFlow()

    fun onAction(event: LoginAction) {
        when (event) {
            is LoginAction.LoginWithEmail -> login(event.email, event.password)
            is LoginAction.SignupWithEmail -> signup(event.email, event.password)
            is LoginAction.OnForgotPasswordClick -> {}
        }
    }

    private fun signup(email: String, password: String) =
        executeAuthAction {
            signUp(email, password)
        }

    private fun login(email: String, password: String) =
        executeAuthAction {
            loginWithEmail(email, password)
        }

    private fun executeAuthAction(
        action: suspend () -> Unit
    ) {
        if (_state.value.isLoading) return
        _state.update { it.copy(isLoading = true) }

        viewModelScope.launch {
            runCatching { action() }
                .onSuccess {
                    _state.update { it.copy(isLoading = false) }
                }
                .onFailure { throwable ->
                    _state.update {
                        when (throwable) {
                            is EmailValidationError ->
                                it.copy(emailValidationError = throwable, isLoading = false)
                            is PasswordValidationError ->
                                it.copy(passwordValidationError = throwable, isLoading = false)
                            is AuthError ->
                                it.copy(authError = throwable, isLoading = false)
                            else ->
                                it.copy(throwable = throwable, isLoading = false)
                        }
                    }
                }
        }
    }

}