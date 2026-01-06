package com.apple.signin.model

sealed class AppleSignInResult {
    data class Success(
        val appleUser: AppleUser
    ) : AppleSignInResult()

    object Cancelled : AppleSignInResult()

    data class Failure(
        val reason: String? = null
    ) : AppleSignInResult()
}

data class AppleUser(
    val idToken: String,
    val authorizationCode: String,
    val email: String?,
    val fullName: String?,
    val rawNonce: String?
)
