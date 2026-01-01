package com.auth.domain.usecases

import com.auth.domain.FakeAuthRepository
import kotlinx.coroutines.runBlocking
import kotlin.test.Test
import kotlin.test.assertTrue

class LogoutTest {

    private val repository = FakeAuthRepository()
    private val useCase = Logout(repository)

    @Test
    fun logout_clears_user_and_marks_logged_out() {
        runBlocking {
            repository.loginWithEmail("user@example.com", "StrongPass1")
            useCase()
            assertTrue(repository.loggedOut)
        }
    }
}