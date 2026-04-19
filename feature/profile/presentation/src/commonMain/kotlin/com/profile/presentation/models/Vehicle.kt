package com.profile.presentation.models

import androidx.compose.runtime.Immutable

@Immutable
data class Vehicle(
    val id: String,
    val name: String,
    val subtitle: String
)