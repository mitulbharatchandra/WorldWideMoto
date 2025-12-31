package com.auth.domain.usecases

import com.auth.domain.FakeAuthRepository
import kotlinx.coroutines.runBlocking
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertFailsWith

class LoginWithEmailTest {

    private val repository = FakeAuthRepository()
    private val useCase = LoginWithEmail(repository)

    @Test
    fun login_with_valid_email_and_password_succeeds() {
        runBlocking {
            val user = useCase(
                email = "user@example.com",
                password = "StrongPass1"
            )

            assertEquals("user@example.com", user.email)
        }
    }

    @Test
    fun login_with_invalid_email_throws() {
        runBlocking {
            assertFailsWith<IllegalArgumentException> {
                useCase(
                    email = "invalid-email",
                    password = "StrongPass1"
                )
            }
        }
    }

    @Test
    fun login_with_blank_password_throws() {
        runBlocking {
            assertFailsWith<IllegalArgumentException> {
                useCase(
                    email = "user@example.com",
                    password = ""
                )
            }
        }
    }
}
