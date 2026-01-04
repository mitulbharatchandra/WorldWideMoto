package com.auth.data.di

import com.auth.data.repository.AuthRepositoryImpl
import com.auth.domain.repository.AuthRepository
import org.koin.dsl.module

val authDataModule = module {

    single<AuthRepository> {
        AuthRepositoryImpl(
            authService = get(),
            signInGoogleProvider = get()
        )
    }
}