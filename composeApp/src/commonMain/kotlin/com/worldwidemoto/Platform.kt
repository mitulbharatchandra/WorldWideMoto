package com.worldwidemoto

import org.koin.core.annotation.Single

@Single
expect class PlatformComponent() {
    fun getInfo() : String
}