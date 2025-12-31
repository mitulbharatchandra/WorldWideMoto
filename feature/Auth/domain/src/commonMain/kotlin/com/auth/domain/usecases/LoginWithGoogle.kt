package com.auth.domain.usecases

import com.auth.domain.repository.AuthRepository
import com.kmp.auth.api.model.AuthUser

class LoginWithGoogle(
    private val repository: AuthRepository
) {

    suspend operator fun invoke(
        idToken: String
    ): AuthUser {
        require(idToken.isNotBlank()) { "Google ID token cannot be empty" }

        return repository.loginWithGoogle(idToken)
    }
}