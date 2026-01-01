package com.auth.domain.usecases

import com.auth.domain.FakeAuthRepository
import kotlinx.coroutines.runBlocking
import kotlin.test.*

class LoginWithPhoneTest {

    private val repository = FakeAuthRepository()
    private val useCase = LoginWithPhone(repository)

    @Test
    fun login_with_valid_phone_and_code_succeeds() {
        runBlocking {
            val user = useCase(
                phoneNumber = "+14155552671",
                verificationCode = "123456"
            )

            assertEquals("+14155552671", user.phoneNumber)
        }
    }

    @Test
    fun login_with_invalid_phone_throws() {
        runBlocking {
            assertFailsWith<IllegalArgumentException> {
                useCase(
                    phoneNumber = "12345",
                    verificationCode = "123456"
                )
            }
        }
    }

    @Test
    fun login_with_blank_code_throws() {
        runBlocking {
            assertFailsWith<IllegalArgumentException> {
                useCase(
                    phoneNumber = "+14155552671",
                    verificationCode = ""
                )
            }
        }
    }
}
