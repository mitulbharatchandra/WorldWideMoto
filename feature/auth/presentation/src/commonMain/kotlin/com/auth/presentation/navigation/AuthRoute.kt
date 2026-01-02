package com.auth.presentation.navigation

import androidx.navigation3.runtime.NavKey
import kotlinx.serialization.Serializable

@Serializable
data object AuthRoute: NavKey {
    @Serializable
    data object Login: NavKey

    @Serializable
    data object SignInWithPhone: NavKey

    @Serializable
    data class ForgotPassword(val email: String? = null): NavKey
}