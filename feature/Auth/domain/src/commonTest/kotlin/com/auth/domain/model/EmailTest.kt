package com.auth.domain.model

import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertFailsWith

class EmailTest {

    @Test
    fun valid_email_creates_email_object() {
        val email = Email.create("user@example.com")
        assertEquals("user@example.com", email.value)
    }

    @Test
    fun email_without_at_sign_throws() {
        assertFailsWith<IllegalArgumentException> {
            Email.create("userexample.com")
        }
    }

    @Test
    fun email_without_domain_throws() {
        assertFailsWith<IllegalArgumentException> {
            Email.create("user@")
        }
    }

    @Test
    fun blank_email_throws() {
        assertFailsWith<IllegalArgumentException> {
            Email.create("")
        }
    }
}