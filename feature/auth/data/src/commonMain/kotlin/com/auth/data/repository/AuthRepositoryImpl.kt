package com.auth.data.repository

import com.auth.domain.repository.AuthRepository
import com.google.sigin.SignInGoogleProvider
import com.google.sigin.model.GoogleSignInError
import com.kmp.auth.api.AuthService
import com.kmp.auth.api.model.AuthCredentials
import com.kmp.auth.api.model.AuthUser

class AuthRepositoryImpl(
    private val authService: AuthService,
    private val signInGoogleProvider: SignInGoogleProvider,
) : AuthRepository {
    override suspend fun loginWithEmail(
        email: String,
        password: String
    ): AuthUser =
        authService.signIn(
            AuthCredentials.EmailPassword(
                email = email,
                password = password
            )
        )

    override suspend fun signUpWithEmail(
        email: String,
        password: String
    ): AuthUser =
        authService.signUp(
            email = email,
            password = password
        )

    override suspend fun loginWithPhone(
        phoneNumber: String,
        verificationCode: String
    ): AuthUser {
        // Phone auth flow can be extended later
        throw UnsupportedOperationException(
            "Phone auth not implemented yet"
        )
    }

    override suspend fun loginWithGoogle(
        webClientId: String,
        authorizedAccounts: Boolean
    ): AuthUser {
        return try {
            val user = signInGoogleProvider.signInWithGoogle(
                clientId = webClientId,
                authorizedAccounts = authorizedAccounts
            )

            authService.loginWithGoogle(user.idToken)
        } catch (throwable: Throwable) {
            if (
                throwable is GoogleSignInError.NoCredentialException &&
                authorizedAccounts
            ) {
                // Retry with non-authorized accounts
                loginWithGoogle(
                    webClientId = webClientId,
                    authorizedAccounts = false
                )
            } else {
                throw throwable
            }
        }
    }

    override suspend fun getCurrentUser(): AuthUser? =
        authService.getCurrentUser()

    override suspend fun logout() =
        authService.signOut()
}
