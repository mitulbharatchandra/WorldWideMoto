package com.worldwidemoto.firebase.auth.di

import com.kmp.auth.api.AuthService
import com.worldwidemoto.firebase.auth.FirebaseAuthService
import org.koin.core.module.Module
import org.koin.dsl.module

actual val firebaseAuthModule: Module = module {
    single<AuthService> { FirebaseAuthService() }
}