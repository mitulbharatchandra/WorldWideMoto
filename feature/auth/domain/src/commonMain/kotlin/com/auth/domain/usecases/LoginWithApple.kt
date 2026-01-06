package com.auth.domain.usecases

import com.auth.domain.repository.AuthRepository
import com.kmp.auth.api.model.AuthUser

class LoginWithApple(
    private val repository: AuthRepository,
) {
    suspend operator fun invoke(): AuthUser {
        return repository.loginWithApple()
    }
}