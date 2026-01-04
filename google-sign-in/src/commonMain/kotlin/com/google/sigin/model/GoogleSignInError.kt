package com.google.sigin.model

sealed class GoogleSignInError : Throwable() {
    object NoCredentialException: GoogleSignInError()
    object OtherException: GoogleSignInError()
}