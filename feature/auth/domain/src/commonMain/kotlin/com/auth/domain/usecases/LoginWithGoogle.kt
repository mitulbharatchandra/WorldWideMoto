package com.auth.domain.usecases

import com.auth.domain.repository.AuthRepository
import com.kmp.auth.api.model.AuthUser

class LoginWithGoogle(
    private val repository: AuthRepository,
) {

    suspend operator fun invoke(
        webClientId: String
    ): AuthUser {
        require(webClientId.isNotBlank()) { "Google ID token cannot be empty" }

        return repository.loginWithGoogle(webClientId)
    }
}