package com.worldwidemoto

@org.koin.core.annotation.Single
actual class PlatformComponent actual constructor() {
    actual fun getInfo(): String {
        return "Native"
    }
}