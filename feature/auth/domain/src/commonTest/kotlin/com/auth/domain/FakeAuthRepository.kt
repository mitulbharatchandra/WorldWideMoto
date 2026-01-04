package com.auth.domain

import com.auth.domain.repository.AuthRepository
import com.kmp.auth.api.model.AuthUser


class FakeAuthRepository : AuthRepository {

    var currentUser: AuthUser? = null
    var loggedOut = false

    override suspend fun loginWithEmail(
        email: String,
        password: String
    ): AuthUser {
        val user = AuthUser(
            id = "email_user",
            email = email,
            isAnonymous = false
        )
        currentUser = user
        return user
    }

    override suspend fun signUpWithEmail(
        email: String,
        password: String
    ): AuthUser {
        val user = AuthUser(
            id = "email_user",
            email = email,
            isAnonymous = false
        )
        currentUser = user
        return user
    }

    override suspend fun loginWithPhone(
        phoneNumber: String,
        verificationCode: String
    ): AuthUser {
        val user = AuthUser(
            id = "phone_user",
            phoneNumber = phoneNumber,
            isAnonymous = false
        )
        currentUser = user
        return user
    }

    override suspend fun loginWithGoogle(
        webClientId: String,
        authorizedAccounts: Boolean
    ): AuthUser {
        val user = AuthUser(
            id = "google_user",
            email = "google@example.com",
            isAnonymous = false
        )
        currentUser = user
        return user
    }

    override suspend fun getCurrentUser(): AuthUser? =
        currentUser

    override suspend fun logout() {
        loggedOut = true
        currentUser = null
    }
}