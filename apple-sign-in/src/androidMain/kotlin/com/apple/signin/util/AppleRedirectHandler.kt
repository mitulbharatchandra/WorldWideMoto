package com.apple.signin.util

import com.apple.signin.model.AppleSignInResult

object AppleRedirectHandler {
    var onResult: ((AppleSignInResult) -> Unit)? = null

    // 🔐 Store rawNonce for the current sign-in attempt
    var rawNonce: String? = null

    fun clear() {
        onResult = null
        rawNonce = null
    }
}
