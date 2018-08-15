package com.gaurav.beerlelo

import android.app.Application

internal class BeerLeloApplication : Application() {

    companion object {

        lateinit var instance: BeerLeloApplication
            private set
    }

    override fun onCreate() {
        super.onCreate()

        instance = this
    }
}