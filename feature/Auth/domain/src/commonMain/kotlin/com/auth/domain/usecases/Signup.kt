package com.auth.domain.usecases

import com.auth.domain.model.Email
import com.auth.domain.model.Password
import com.auth.domain.repository.AuthRepository
import com.kmp.auth.api.model.AuthUser

class Signup(
    private val repository: AuthRepository
) {
    suspend operator fun invoke(
        email: String,
        password: String
    ): AuthUser {
        val validEmail = Email.create(email)
        val validPassword = Password.create(password)

        return repository.signUpWithEmail(validEmail.value, validPassword.value)
    }
}