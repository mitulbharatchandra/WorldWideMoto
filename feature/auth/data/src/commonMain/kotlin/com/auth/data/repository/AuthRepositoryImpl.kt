package com.auth.data.repository

import com.auth.domain.repository.AuthRepository
import com.kmp.auth.api.AuthService
import com.kmp.auth.api.model.AuthCredentials
import com.kmp.auth.api.model.AuthUser

class AuthRepositoryImpl(
    private val authService: AuthService
) : AuthRepository {
    override suspend fun loginWithEmail(
        email: String,
        password: String
    ): AuthUser =
        authService.signIn(
            AuthCredentials.EmailPassword(
                email = email,
                password = password
            )
        )

    override suspend fun signUpWithEmail(
        email: String,
        password: String
    ): AuthUser =
        authService.signUp(
            email = email,
            password = password
        )

    override suspend fun loginWithPhone(
        phoneNumber: String,
        verificationCode: String
    ): AuthUser {
        // Phone auth flow can be extended later
        throw UnsupportedOperationException(
            "Phone auth not implemented yet"
        )
    }

    override suspend fun loginWithGoogle(
        idToken: String
    ): AuthUser {
        // Google auth will be added later
        throw UnsupportedOperationException(
            "Google auth not implemented yet"
        )
    }

    override suspend fun getCurrentUser(): AuthUser? =
        authService.getCurrentUser()

    override suspend fun logout() =
        authService.signOut()
}
