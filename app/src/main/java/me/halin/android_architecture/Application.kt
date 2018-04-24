package me.halin.android_architecture

import android.app.Application

class Application : Application() {


    companion object {
        lateinit var application: me.halin.android_architecture.Application
            private set
    }

    override fun onCreate() {
        super.onCreate()
        application = this
    }

}