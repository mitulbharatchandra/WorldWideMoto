package com.auth.domain.usecases
import com.auth.domain.FakeAuthRepository
import kotlinx.coroutines.runBlocking
import kotlin.test.*

class SignupTest {

    private val repository = FakeAuthRepository()
    private val useCase = Signup(repository)

    @Test
    fun signup_with_valid_email_and_password_succeeds() {
        runBlocking {
            val user = useCase(
                email = "user@example.com",
                password = "StrongPass1"
            )

            assertEquals("user@example.com", user.email)
        }
    }

    @Test
    fun signup_with_invalid_email_throws() {
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
    fun signup_with_blank_password_throws() {
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
