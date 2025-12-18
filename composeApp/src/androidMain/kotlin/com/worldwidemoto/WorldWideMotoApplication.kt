package com.worldwidemoto

import android.app.Application
import com.worldwidemoto.di.initKoin

class WorldWideMotoApplication : Application() {

    override fun onCreate() {
        super.onCreate()

        initKoin()
    }
}