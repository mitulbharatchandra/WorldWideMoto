@file:OptIn(ExperimentalForeignApi::class)

package com.worldwidemoto.firebase.auth

import cocoapods.FirebaseAuth.FIRAuthErrorDomain
import cocoapods.FirebaseAuth.FIRMultiFactorResolver
import com.kmp.auth.api.model.AuthError
import kotlinx.cinterop.ExperimentalForeignApi
import platform.Foundation.NSError

internal fun NSError.toAuthError() = when (domain) {
    // codes from AuthErrors.swift: https://github.com/firebase/firebase-ios-sdk/blob/
    // 2f6ac4c2c61cd57c7ea727009e187b7e1163d613/FirebaseAuth/Sources/Swift/Utilities/
    // AuthErrors.swift#L51
    FIRAuthErrorDomain -> when (code) {
        17030L, // AuthErrorCode.invalidActionCode
        17029L, // AuthErrorCode.expiredActionCode
            -> AuthError.InvalidActionCode

        17008L, // AuthErrorCode.invalidEmail
            -> AuthError.InvalidEmail

        17056L, // AuthErrorCode.captchaCheckFailed
        17042L, // AuthErrorCode.invalidPhoneNumber
        17041L, // AuthErrorCode.missingPhoneNumber
        17046L, // AuthErrorCode.invalidVerificationID
        17044L, // AuthErrorCode.invalidVerificationCode
        17045L, // AuthErrorCode.missingVerificationID
        17043L, // AuthErrorCode.missingVerificationCode
        17021L, // AuthErrorCode.userTokenExpired
        17004L, // AuthErrorCode.invalidCredential
            -> AuthError.InvalidCredentials

        17026L, // AuthErrorCode.weakPassword
            -> AuthError.WeakPassword

        17017L, // AuthErrorCode.invalidUserToken
            -> AuthError.InvalidCredentials

        17014L, // AuthErrorCode.requiresRecentLogin
            -> AuthError.RecentLoginRequired

        17087L, // AuthErrorCode.secondFactorAlreadyEnrolled
        17078L, // AuthErrorCode.secondFactorRequired
        17088L, // AuthErrorCode.maximumSecondFactorCountExceeded
        17084L, // AuthErrorCode.multiFactorInfoNotFound
            -> {
            val resolver =
                userInfo["FIRAuthErrorUserInfoMultiFactorResolverKey"] as? FIRMultiFactorResolver
            AuthError.AuthMultiFactor(toString())
        }

        17052L, // AuthErrorCode.quotaExceeded
            -> AuthError.TooManyRequests

        17007L, // AuthErrorCode.emailAlreadyInUse
        17012L, // AuthErrorCode.accountExistsWithDifferentCredential
        17025L, // AuthErrorCode.credentialAlreadyInUse
            -> AuthError.EmailAlreadyInUse

        17057L, // AuthErrorCode.webContextAlreadyPresented
        17058L, // AuthErrorCode.webContextCancelled
        17062L, // AuthErrorCode.webInternalError
            -> AuthError.Unknown(toString())

        17020L, // AuthErrorCode.networkError
            -> AuthError.NetworkError

        else -> AuthError.Unknown(toString())
    }

    else -> AuthError.Unknown(toString())
}