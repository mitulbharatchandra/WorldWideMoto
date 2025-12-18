package com.worldwidemoto

import android.os.Build
import org.koin.core.annotation.Single

@Single
actual class PlatformComponent actual constructor() {
    actual fun getInfo(): String {
        return "Android ${Build.VERSION.SDK_INT}"
    }
}