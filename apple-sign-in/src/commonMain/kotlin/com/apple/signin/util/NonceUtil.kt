package com.apple.signin.util

import kotlin.random.Random


object NonceUtil {
    fun generateRawNonce(length: Int = 32): String {
        val charset =
            "0123456789ABCDEFGHIJKLMNOPQRSTUVXYZabcdefghijklmnopqrstuvwxyz-._"
        return buildString {
            repeat(length) {
                append(charset[Random.nextInt(charset.length)])
            }
        }
    }

}