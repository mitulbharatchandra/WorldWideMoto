package com.profile.presentation.models

import androidx.compose.runtime.Immutable

@Immutable
data class UserProfile(
    val name: String,
    val email: String,
    val avatarUrl: String? = null
)