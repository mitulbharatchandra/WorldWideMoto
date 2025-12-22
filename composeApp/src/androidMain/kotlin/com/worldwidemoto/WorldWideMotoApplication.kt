package com.worldwidemoto

import android.app.Application
import com.worldwidemoto.di.initKoinMitul
import org.koin.android.ext.koin.androidContext

class WorldWideMotoApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        initKoinMitul {
            androidContext(this@WorldWideMotoApplication)
        }
    }
}