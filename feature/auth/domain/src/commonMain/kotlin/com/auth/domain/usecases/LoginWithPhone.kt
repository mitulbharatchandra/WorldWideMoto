package com.auth.domain.usecases

import com.auth.domain.model.PhoneNumber
import com.auth.domain.repository.AuthRepository
import com.kmp.auth.api.model.AuthUser

class LoginWithPhone(
    private val repository: AuthRepository
) {

    suspend operator fun invoke(
        phoneNumber: String,
        verificationCode: String
    ): AuthUser {
        val validPhoneNumber = PhoneNumber.create(phoneNumber)

        require(verificationCode.isNotBlank()) {
            "Verification code cannot be empty"
        }

        return repository.loginWithPhone(
            validPhoneNumber.value,
            verificationCode
        )
    }
}