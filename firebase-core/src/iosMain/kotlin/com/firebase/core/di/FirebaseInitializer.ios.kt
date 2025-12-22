package com.firebase.core.di

import cocoapods.FirebaseCore.FIRApp
import cocoapods.FirebaseAnalytics.FIRAnalytics
import kotlinx.cinterop.ExperimentalForeignApi

actual class FirebaseInitializer {
    @OptIn(ExperimentalForeignApi::class)
    actual fun initialize() {
        if (FIRApp.defaultApp() == null) {
            FIRApp.configure()
            FIRAnalytics.logEventWithName(
                name = "share_image",
                parameters = mapOf(
                    "name" to "name",
                    "full_text" to "text",
                )
            )
        }
    }
}