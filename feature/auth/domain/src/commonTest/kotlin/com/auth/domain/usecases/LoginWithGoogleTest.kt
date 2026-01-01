package com.auth.domain.usecases

import com.auth.domain.FakeAuthRepository
import kotlinx.coroutines.runBlocking
import kotlin.test.*

class LoginWithGoogleTest {

    private val repository = FakeAuthRepository()
    private val useCase = LoginWithGoogle(repository)

    @Test
    fun login_with_valid_google_token_succeeds() {
        runBlocking {
            val user = useCase("valid_token")
            assertEquals("google_user", user.id)
        }
    }

    @Test
    fun login_with_blank_token_throws() {
        runBlocking {
            assertFailsWith<IllegalArgumentException> {
                useCase("")
            }
        }
    }
}
