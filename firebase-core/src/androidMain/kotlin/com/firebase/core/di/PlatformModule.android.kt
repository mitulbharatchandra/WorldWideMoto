package com.firebase.core.di

import org.koin.android.ext.koin.androidContext
import org.koin.core.module.Module
import org.koin.dsl.module

actual val platformModule: Module = module {
    single { FirebaseInitializer(androidContext()) }
}