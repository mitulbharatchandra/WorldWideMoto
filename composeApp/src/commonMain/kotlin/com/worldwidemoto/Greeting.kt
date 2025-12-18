package com.worldwidemoto

class Greeting {
    private val platform = getPlatform()

    fun greet(): String {
        return "Hello Mit, ${platform.name}!"
    }
}