package com.worldwidemoto.firebase.auth.mappers

import com.google.firebase.auth.FirebaseUser
import com.kmp.auth.api.model.AuthUser

internal fun FirebaseUser.toAuthUser(): AuthUser =
    AuthUser(
        id = uid,
        email = email,
        isAnonymous = isAnonymous,
        isEmailVerified = isEmailVerified
    )