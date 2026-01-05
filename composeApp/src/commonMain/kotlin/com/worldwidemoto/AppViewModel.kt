package com.worldwidemoto

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.auth.domain.usecases.GetCurrentUser
import com.kmp.auth.api.model.AuthUser
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class AppViewModel(
    private val getCurrentUser: GetCurrentUser
): ViewModel() {
    private val _state = MutableStateFlow(AppUIState())
    val state = _state
        .onStart {
            executeAuthAction(
                action = { getCurrentUser() }
            )
        }
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5_000L),
            initialValue = AppUIState()
        )

    private fun executeAuthAction(
        action: suspend () -> AuthUser?
    ) {
        _state.update { it.copy(isLoading = true) }

        viewModelScope.launch {
            runCatching { action() }
                .onSuccess { authUser ->
                    _state.update {
                        it.copy(
                            isLoading = false,
                            isLoggedIn = authUser != null
                        )
                    }
                }
                .onFailure { throwable ->
                    _state.update {
                        it.copy(
                            errorMessage = throwable.message,
                            isLoading = false
                        )
                    }
                }
        }
    }
}