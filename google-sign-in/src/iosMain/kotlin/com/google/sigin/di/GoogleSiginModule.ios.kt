package com.google.sigin.di

import com.google.sigin.SignInGoogleProvider
import org.koin.core.module.Module
import org.koin.dsl.module

actual val signInWithGoogleModule: Module = module {
    single { SignInGoogleProvider() }
}