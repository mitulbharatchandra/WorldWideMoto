package com.auth.domain.model
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertFalse
import kotlin.test.assertTrue
import kotlin.test.assertFailsWith

class PasswordTest {

    @Test
    fun valid_password_passes_validation() {
        val validation = Password.validate("StrongPass1")

        assertTrue(validation.hasMinLength)
        assertTrue(validation.hasUpperCaseCharacter)
        assertTrue(validation.hasLowerCaseCharacter)
        assertTrue(validation.hasNumber)
        assertTrue(validation.isValid)
    }

    @Test
    fun password_without_uppercase_is_invalid() {
        val validation = Password.validate("strongpass1")
        assertFalse(validation.hasUpperCaseCharacter)
        assertFalse(validation.isValid)
    }

    @Test
    fun password_without_number_is_invalid() {
        val validation = Password.validate("StrongPass")
        assertFalse(validation.hasNumber)
        assertFalse(validation.isValid)
    }

    @Test
    fun password_shorter_than_minimum_length_is_invalid() {
        val validation = Password.validate("S1a")
        assertFalse(validation.hasMinLength)
        assertFalse(validation.isValid)
    }

    @Test
    fun create_throws_for_invalid_password() {
        assertFailsWith<IllegalArgumentException> {
            Password.create("weak")
        }
    }

    @Test
    fun create_succeeds_for_valid_password() {
        val password = Password.create("StrongPass1")
        assertEquals("StrongPass1", password.value)
    }
}
