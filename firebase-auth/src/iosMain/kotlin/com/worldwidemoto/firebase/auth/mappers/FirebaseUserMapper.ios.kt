@file:OptIn(ExperimentalForeignApi::class)

package com.worldwidemoto.firebase.auth.mappers

import cocoapods.FirebaseAuth.FIRUser
import com.kmp.auth.api.model.AuthUser
import kotlinx.cinterop.ExperimentalForeignApi

internal fun FIRUser.toAuthUser(): AuthUser =
    AuthUser(
        id = uid(),
        email = email(),
        isAnonymous = isAnonymous(),
        isEmailVerified = isEmailVerified(),
        displayName = displayName(),
        photoUrl = photoURL()?.absoluteString
    )