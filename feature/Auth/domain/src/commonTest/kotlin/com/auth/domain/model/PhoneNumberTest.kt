package com.auth.domain.model

import kotlin.test.Test
import kotlin.test.assertFalse
import kotlin.test.assertTrue
import kotlin.test.assertFailsWith
import kotlin.test.assertEquals

class PhoneNumberTest {

    @Test
    fun valid_E164_phone_number_passes_validation() {
        val validation = PhoneNumber.validate("+14155552671")
        assertTrue(validation.isValid)
    }

    @Test
    fun phone_number_without_plus_sign_is_invalid() {
        val validation = PhoneNumber.validate("14155552671")
        assertFalse(validation.hasValidCountryCode)
        assertFalse(validation.isValid)
    }

    @Test
    fun phone_number_with_letters_is_invalid() {
        val validation = PhoneNumber.validate("+14155ABC671")
        assertFalse(validation.hasOnlyDigits)
        assertFalse(validation.isValid)
    }

    @Test
    fun blank_phone_number_is_invalid() {
        val validation = PhoneNumber.validate("")
        assertFalse(validation.isNotBlank)
        assertFalse(validation.isValid)
    }

    @Test
    fun create_throws_for_invalid_phone_number() {
        assertFailsWith<IllegalArgumentException> {
            PhoneNumber.create("12345")
        }
    }

    @Test
    fun create_succeeds_for_valid_phone_number() {
        val phone = PhoneNumber.create("+919876543210")
        assertEquals("+919876543210", phone.value)
    }
}
