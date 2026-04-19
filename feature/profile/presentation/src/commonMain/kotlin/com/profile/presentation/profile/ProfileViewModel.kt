package com.profile.presentation.profile

import androidx.lifecycle.ViewModel
import com.auth.domain.usecases.GetCurrentUser
import com.auth.domain.usecases.Logout

class ProfileViewModel(
    private val logout: Logout,
    private val getCurrentUser: GetCurrentUser
) : ViewModel() {

}