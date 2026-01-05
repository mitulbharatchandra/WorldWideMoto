package com.worldwidemoto

sealed interface AppEvent {
    data object OnReceivedCurrentUser: AppEvent
}