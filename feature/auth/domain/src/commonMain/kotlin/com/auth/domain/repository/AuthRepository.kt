package com.auth.domain.repository

import com.kmp.auth.api.model.AuthUser

interface AuthRepository {
    suspend fun loginWithEmail(
        email: String,
        password: String
    ): AuthUser

    suspend fun signUpWithEmail(
        email: String,
        password: String
    ): AuthUser

    suspend fun loginWithPhone(
        phoneNumber: String,
        verificationCode: String
    ): AuthUser

    suspend fun loginWithGoogle(
        webClientId: String,
        authorizedAccounts: Boolean = true
    ): AuthUser

    suspend fun loginWithApple(): AuthUser

    suspend fun getCurrentUser(): AuthUser?

    suspend fun logout()
}