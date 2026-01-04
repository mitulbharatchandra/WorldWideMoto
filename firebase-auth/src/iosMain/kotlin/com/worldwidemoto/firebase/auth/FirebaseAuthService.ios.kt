@file:OptIn(ExperimentalForeignApi::class)

package com.worldwidemoto.firebase.auth
import cocoapods.FirebaseAuth.FIRAuth
import cocoapods.FirebaseAuth.FIRGoogleAuthProvider
import com.kmp.auth.api.AuthService
import com.kmp.auth.api.model.AuthCredentials
import com.kmp.auth.api.model.AuthError
import com.kmp.auth.api.model.AuthUser
import com.worldwidemoto.firebase.auth.mappers.toAuthUser
import kotlinx.cinterop.ExperimentalForeignApi
import kotlinx.coroutines.suspendCancellableCoroutine
import kotlin.coroutines.resume
import kotlin.coroutines.resumeWithException

actual class FirebaseAuthService: AuthService {

    private val auth by lazy { FIRAuth.auth() }

    override suspend fun getCurrentUser(): AuthUser? =
        auth.currentUser()?.toAuthUser()

    override suspend fun signIn(credentials: AuthCredentials): AuthUser {
        return suspendCancellableCoroutine { cont ->
            when (credentials) {
                is AuthCredentials.EmailPassword -> {
                    auth.signInWithEmail(
                        email = credentials.email,
                        password = credentials.password
                    ) { result, error ->
                        when {
                            error != null ->
                                cont.resumeWithException(
                                    error.toAuthError()
                                )

                            result?.user() != null ->
                                cont.resume(result.user().toAuthUser())

                            else ->
                                cont.resumeWithException(
                                    AuthError.Unknown("Firebase returned empty result")
                                )
                        }
                    }
                }
                is AuthCredentials.Anonymous -> {
                    cont.resumeWithException(
                        AuthError.Unknown("Firebase returned empty result")
                    )
                }
            }
        }
    }

    override suspend fun signUp(email: String, password: String): AuthUser {
        return suspendCancellableCoroutine { cont ->
            auth.createUserWithEmail(
                email,
                password = password
            ) { result, error ->

                when {
                    error != null ->
                        cont.resumeWithException(
                            error.toAuthError()
                        )

                    result?.user() != null ->
                        cont.resume(result.user().toAuthUser())

                    else ->
                        cont.resumeWithException(
                            AuthError.Unknown(
                                "Firebase returned empty signUp result"
                            )
                        )
                }
            }
        }
    }

    override suspend fun sendPasswordResetEmail(email: String) {

    }

    override suspend fun loginWithGoogle(idToken: String): AuthUser {
        return suspendCancellableCoroutine { cont ->
            val credentials = FIRGoogleAuthProvider.credentialWithIDToken(idToken = idToken, accessToken = "")
            auth.signInWithCredential(
                credential = credentials
            ) { result, error ->

                when {
                    error != null ->
                        cont.resumeWithException(
                            error.toAuthError()
                        )

                    result?.user() != null ->
                        cont.resume(result.user().toAuthUser())

                    else ->
                        cont.resumeWithException(
                            AuthError.Unknown(
                                "Firebase returned empty signUp result"
                            )
                        )
                }
            }
        }
    }

    override suspend fun signOut() {
        auth.signOut(error = null)
    }

}