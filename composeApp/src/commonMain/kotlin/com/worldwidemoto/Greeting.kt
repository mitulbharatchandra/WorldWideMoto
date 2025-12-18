package com.worldwidemoto

class Greeting {
    private val platform = PlatformComponent()

    fun greet(): String {
        return "Hello Mit, ${platform.getInfo()}!"
    }
}