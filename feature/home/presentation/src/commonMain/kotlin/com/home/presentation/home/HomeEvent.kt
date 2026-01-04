package com.home.presentation.home

sealed interface HomeEvent {
    data object LogoutSuccess: HomeEvent
}