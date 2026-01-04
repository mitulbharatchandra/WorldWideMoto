package com.google.sigin

import com.google.sigin.model.GoogleUser

expect class SignInGoogleProvider {
    suspend fun signInWithGoogle(clientId: String, authorizedAccounts: Boolean = true): GoogleUser
    suspend fun signOut()
}