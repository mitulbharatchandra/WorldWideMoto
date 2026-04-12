package com.home.presentation.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.auth.domain.usecases.GetCurrentUser
import com.auth.domain.usecases.Logout
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class HomeViewModel (
    private val logout: Logout,
    private val getCurrentUser: GetCurrentUser
): ViewModel() {

    private val _state = MutableStateFlow(HomeUIState())
    val state = _state.asStateFlow()

    private val eventChannel = Channel<HomeEvent>()
    val events = eventChannel.receiveAsFlow()

    init {
        getCurrentUserData()
    }

    private fun getCurrentUserData() {
        viewModelScope.launch {
            runCatching {
                getCurrentUser()
            }.onSuccess { user ->
                _state.update {
                    it.copy(
                        userProfileUrl = user?.photoUrl
                    )
                }
            }.onFailure {
                println(it.message)
            }
        }
    }

    fun signOut() {
        if (_state.value.isLoading) return
        _state.update { it.copy(isLoading = true) }
        viewModelScope.launch {
            runCatching { logout() }
                .onSuccess {
                    eventChannel.send(HomeEvent.LogoutSuccess)
                }
                .onFailure { t ->
                    println(t.message)
                    _state.update { it.copy(isLoading = false) }
                }
        }
    }
}