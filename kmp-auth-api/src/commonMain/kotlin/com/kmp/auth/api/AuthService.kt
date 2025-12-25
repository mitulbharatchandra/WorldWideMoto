package com.kmp.auth.api

import com.kmp.auth.api.model.AuthCredentials
import com.kmp.auth.api.model.AuthUser

interface AuthService {
    suspend fun getCurrentUser(): AuthUser?
    suspend fun signIn(credentials: AuthCredentials): AuthUser
    suspend fun signUp(
        email: String,
        password: String
    ): AuthUser
    suspend fun sendPasswordResetEmail(email: String)
    suspend fun signOut()
}