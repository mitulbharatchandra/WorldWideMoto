package com.firebase.core.di

import org.koin.core.module.Module
import org.koin.dsl.module

actual val firebaseCoreModule: Module = module {
    single { FirebaseInitializer() }
}