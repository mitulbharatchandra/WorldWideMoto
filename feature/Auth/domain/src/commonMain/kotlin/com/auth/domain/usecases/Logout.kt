package com.auth.domain.usecases

import com.auth.domain.repository.AuthRepository

class Logout(
    private val repository: AuthRepository
) {

    suspend operator fun invoke() =
        repository.logout()
}
