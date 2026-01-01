package com.auth.domain.usecases

import com.auth.domain.repository.AuthRepository
import com.kmp.auth.api.model.AuthUser

class GetCurrentUser(
    private val repository: AuthRepository
) {

    suspend operator fun invoke(): AuthUser? =
        repository.getCurrentUser()
}