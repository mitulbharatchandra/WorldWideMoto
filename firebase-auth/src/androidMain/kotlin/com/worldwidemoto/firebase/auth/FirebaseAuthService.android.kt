package com.worldwidemoto.firebase.auth

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.GoogleAuthProvider
import com.kmp.auth.api.AuthService
import com.kmp.auth.api.model.AuthCredentials
import com.kmp.auth.api.model.AuthError
import com.kmp.auth.api.model.AuthUser
import com.worldwidemoto.firebase.auth.mappers.toAuthError
import com.worldwidemoto.firebase.auth.mappers.toAuthUser
import kotlinx.coroutines.tasks.await

actual class FirebaseAuthService: AuthService {
    private val auth by lazy { FirebaseAuth.getInstance() }
    override suspend fun getCurrentUser(): AuthUser? =
        auth.currentUser?.toAuthUser()

    override suspend fun signIn(credentials: AuthCredentials): AuthUser {
        return try {
            when (credentials) {
                AuthCredentials.Anonymous -> {
                    val result = auth.signInAnonymously().await()
                    val user = result.user
                        ?: throw AuthError.Unknown("Firebase returned null user")
                    user.toAuthUser()
                }

                is AuthCredentials.EmailPassword -> {
                    val result = auth
                        .signInWithEmailAndPassword(
                            credentials.email,
                            credentials.password
                        )
                        .await()
                    val user = result.user
                        ?: throw AuthError.Unknown("Firebase returned null user")

                    user.toAuthUser()
                }
            }
        } catch (e: Throwable) {
            throw e.toAuthError()
        }
    }

    override suspend fun signUp(
        email: String,
        password: String
    ): AuthUser {
        return try {
            val result = auth
                .createUserWithEmailAndPassword(email, password)
                .await()

            val user = result.user
                ?: throw AuthError.Unknown("Firebase returned null user")

            user.toAuthUser()

        } catch (e: Throwable) {
            throw e.toAuthError()
        }
    }

    override suspend fun sendPasswordResetEmail(email: String) {
        TODO("Not yet implemented")
    }

    override suspend fun loginWithGoogle(idToken: String): AuthUser {
        return try {
            val credentials = GoogleAuthProvider.getCredential(idToken, null)
            val result = auth
                .signInWithCredential(credentials)
                .await()

            val user = result.user
                ?: throw AuthError.Unknown("Firebase returned null user")

            user.toAuthUser()

        } catch (e: Throwable) {
            throw e.toAuthError()
        }
    }

    override suspend fun signOut() {
        auth.signOut()
    }

}