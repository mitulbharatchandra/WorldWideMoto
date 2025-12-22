package com.firebase.core.error

sealed class FirebaseError {
    object NotInitialized : FirebaseError()
    data class Unknown(val message: String?) : FirebaseError()
}