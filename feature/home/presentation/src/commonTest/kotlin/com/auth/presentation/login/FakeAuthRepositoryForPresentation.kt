package com.auth.presentation.login

import com.auth.domain.repository.AuthRepository
import com.kmp.auth.api.model.AuthUser

class FakeAuthRepositoryForPresentation : AuthRepository {

    var shouldThrow: Throwable? = null
    var loginInvoked = false
    var signupInvoked = false

    override suspend fun loginWithEmail(
        email: String,
        password: String
    ): AuthUser {
        loginInvoked = true
        shouldThrow?.let { throw it }
        return AuthUser(id = "login-user", email = email)
    }

    override suspend fun signUpWithEmail(
        email: String,
        password: String
    ): AuthUser {
        signupInvoked = true
        shouldThrow?.let { throw it }
        return AuthUser(id = "signup-user", email = email)
    }

    override suspend fun loginWithPhone(
        phoneNumber: String,
        verificationCode: String
    ): AuthUser {
        signupInvoked = true
        shouldThrow?.let { throw it }
        return AuthUser(id = "signup-user", phoneNumber = phoneNumber)
    }

    override suspend fun loginWithGoogle(webClientId: String, authorizedAccounts: Boolean): AuthUser {
        signupInvoked = true
        shouldThrow?.let { throw it }
        return AuthUser(id = "signup-user")
    }

    override suspend fun getCurrentUser(): AuthUser? = null
    override suspend fun logout() {}
}
