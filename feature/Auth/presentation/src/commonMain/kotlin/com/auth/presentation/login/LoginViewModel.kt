package com.auth.presentation.login

import androidx.lifecycle.ViewModel
import com.auth.domain.usecases.GetCurrentUser
import com.auth.domain.usecases.LoginWithEmail
import com.auth.domain.usecases.Signup

class LoginViewModel(
    private val loginWithEmail: LoginWithEmail,
    private val signUp: Signup,
    private val getCurrentUser: GetCurrentUser,
): ViewModel() {


}