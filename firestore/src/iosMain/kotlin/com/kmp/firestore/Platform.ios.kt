@file:OptIn(ExperimentalForeignApi::class)

package com.kmp.firestore

import cocoapods.FirebaseFirestoreInternal.FIRFirestore
import kotlinx.cinterop.ExperimentalForeignApi

actual fun platform(): String {
    FIRFirestore.firestore()
    return "IOS"
}