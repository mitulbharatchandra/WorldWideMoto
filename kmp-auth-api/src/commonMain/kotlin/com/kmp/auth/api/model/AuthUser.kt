package com.kmp.auth.api.model

data class AuthUser(
    val id: String,
    val email: String? = null,
    val isAnonymous: Boolean = false,
    val isEmailVerified: Boolean = false,
    val displayName: String? = null,
    val photoUrl: String? = null,
)