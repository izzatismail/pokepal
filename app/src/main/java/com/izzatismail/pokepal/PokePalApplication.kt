package com.izzatismail.pokepal

import android.app.Application
import com.izzatismail.pokepal.di.allModules
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class PokePalApplication: Application() {
    override fun onCreate() {

        super.onCreate()
        startKoin {
            androidContext(this@PokePalApplication)
            modules(allModules)
        }
    }
}