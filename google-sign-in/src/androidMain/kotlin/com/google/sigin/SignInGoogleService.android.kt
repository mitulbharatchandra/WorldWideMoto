package com.google.sigin

import android.content.Context
import android.util.Log
import androidx.credentials.ClearCredentialStateRequest
import androidx.credentials.CredentialManager
import androidx.credentials.GetCredentialRequest
import androidx.credentials.exceptions.GetCredentialCancellationException
import androidx.credentials.exceptions.GetCredentialCustomException
import androidx.credentials.exceptions.GetCredentialException
import androidx.credentials.exceptions.NoCredentialException
import com.google.android.libraries.identity.googleid.GetGoogleIdOption
import com.google.android.libraries.identity.googleid.GoogleIdTokenCredential
import com.google.android.libraries.identity.googleid.GoogleIdTokenParsingException
import com.google.sigin.model.GoogleSignInError
import com.google.sigin.model.GoogleUser
import kotlinx.coroutines.delay
import java.security.SecureRandom
import java.util.Base64

actual class SignInGoogleProvider(private val context: Context) {

    actual suspend fun signInWithGoogle(clientId: String, authorizedAccounts: Boolean): GoogleUser {
        val googleIdOption: GetGoogleIdOption = GetGoogleIdOption.Builder()
            .setFilterByAuthorizedAccounts(authorizedAccounts)
            .setServerClientId(clientId)
            .setNonce(generateSecureRandomNonce())
            .build()

        // Create a credential request with the Google ID option.
        val request: GetCredentialRequest = GetCredentialRequest.Builder()
            .addCredentialOption(googleIdOption)
            .build()

        // Attempt to sign in with the created request using an authorized account
        val googleUser = signIn(request)
        return googleUser
    }

    private fun generateSecureRandomNonce(byteLength: Int = 32): String {
        val randomBytes = ByteArray(byteLength)
        SecureRandom.getInstanceStrong().nextBytes(randomBytes)
        return Base64.getUrlEncoder().withoutPadding().encodeToString(randomBytes)
    }

    //This code will not work on Android versions < UPSIDE_DOWN_CAKE when GetCredentialException is
//is thrown.
    suspend fun signIn(request: GetCredentialRequest): GoogleUser {
        val credentialManager = CredentialManager.create(context)
        val failureMessage = "Sign in failed!"
        val TAG = "CredentialManager"
        //using delay() here helps prevent NoCredentialException when the BottomSheet Flow is triggered
        //on the initial running of our app
        delay(250)
        return try {
            // The getCredential is called to request a credential from Credential Manager.
            val result = credentialManager.getCredential(
                request = request,
                context = context,
            )
            val googleIdTokenCredential = GoogleIdTokenCredential.createFrom(result.credential.data)
            Log.i(TAG, googleIdTokenCredential.profilePictureUri.toString())

            Log.i(TAG, "Sign in Successful!")
            GoogleUser(
                idToken = googleIdTokenCredential.idToken,
                email = googleIdTokenCredential.id,
                displayName = googleIdTokenCredential.displayName,
                profilePicUrl = googleIdTokenCredential.profilePictureUri.toString()
            )
        } catch (e: NoCredentialException) {
            throw GoogleSignInError.NoCredentialException
        } catch (e: GetCredentialCustomException) {
            Log.e(TAG, "$failureMessage: Issue with custom credential request", e)
            throw e
        } catch (e: GetCredentialCancellationException) {
            Log.e(TAG, "$failureMessage: Sign-in was cancelled", e)
            throw e
        } catch (e: GoogleIdTokenParsingException) {
            Log.e(TAG, "$failureMessage: Issue with parsing received GoogleIdToken", e)
            throw e
        } catch (e: GetCredentialException) {
            Log.e(TAG, "$failureMessage: Failure getting credentials", e)
            throw e
        }
    }

    actual suspend fun signOut() {
        val credentialManager = CredentialManager.create(context)
        credentialManager.clearCredentialState(ClearCredentialStateRequest())
    }

}