@file:OptIn(ExperimentalForeignApi::class)

package com.google.sigin

import cocoapods.GoogleSignIn.GIDSignIn
import com.google.sigin.model.GoogleUser
import kotlinx.cinterop.ExperimentalForeignApi
import platform.UIKit.UIApplication
import kotlin.coroutines.resume
import kotlin.coroutines.resumeWithException
import kotlin.coroutines.suspendCoroutine

actual class SignInGoogleProvider() {

    actual suspend fun signInWithGoogle(clientId: String, authorizedAccounts: Boolean): GoogleUser = suspendCoroutine { continutation ->

        val rootViewController =
            UIApplication.sharedApplication.keyWindow?.rootViewController

        if (rootViewController == null) continutation.resumeWithException(Throwable(message = "rootViewController not found"))
        else {
            GIDSignIn.sharedInstance
                .signInWithPresentingViewController(rootViewController,null, null) { gidSignInResult, nsError ->
                    nsError?.let { error -> continutation.resumeWithException(Throwable(message = error.localizedDescription)) }
                    val user = gidSignInResult?.user
                    val idToken = user?.idToken?.tokenString
                    val accessToken = user?.accessToken?.tokenString
                    val profile = gidSignInResult?.user?.profile
                    if (idToken != null) {
                        val googleUser = GoogleUser(
                            idToken = idToken,
                            accessToken = accessToken,
                            email = profile?.email,
                            serverAuthCode = gidSignInResult.serverAuthCode,
                            displayName = profile?.name ?: "",
                            profilePicUrl = profile?.imageURLWithDimension(320u)?.absoluteString
                        )
                        continutation.resume(googleUser)
                    } else continutation.resumeWithException(Throwable(message = "idToken not found"))
                }

        }
    }

    actual suspend fun signOut() {

    }
}