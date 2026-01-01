package com.auth.domain.usecases

import com.auth.domain.FakeAuthRepository
import kotlinx.coroutines.runBlocking
import kotlin.test.*

class GetCurrentUserTest {

    private val repository = FakeAuthRepository()
    private val useCase = GetCurrentUser(repository)

    @Test
    fun returns_null_when_no_user_logged_in() {
        runBlocking {
            val user = useCase()
            assertNull(user)
        }
    }

    @Test
    fun returns_user_when_logged_in() {
        runBlocking {
            repository.loginWithEmail("user@example.com", "StrongPass1")
            val user = useCase()
            assertNotNull(user)
        }
    }
}
