package com.profile.presentation.models

import androidx.compose.runtime.Immutable

@Immutable
data class Address(
    val id: String,
    val label: String,
    val address: String
)