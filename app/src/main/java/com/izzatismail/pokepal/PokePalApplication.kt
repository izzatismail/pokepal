package com.izzatismail.pokepal

import android.app.Application

class PokePalApplication: Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@LylynApplication)
            modules(allModules)
        }
    }
}