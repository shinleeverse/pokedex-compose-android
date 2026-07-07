package com.shinleeverse.pokedex

import android.app.Application
import timber.log.Timber

class PokedexApplication : Application() {

    override fun onCreate() {
        super.onCreate()

        if(BuildConfig.DEBUG){
            Timber.plant(Timber.DebugTree())
        }
    }

}