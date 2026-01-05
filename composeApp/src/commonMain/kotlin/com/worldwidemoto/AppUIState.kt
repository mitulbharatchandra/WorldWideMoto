package com.worldwidemoto

data class AppUIState(
    val isLoading: Boolean = true,
    val isLoggedIn: Boolean = false,
    val errorMessage: String? = null,
)