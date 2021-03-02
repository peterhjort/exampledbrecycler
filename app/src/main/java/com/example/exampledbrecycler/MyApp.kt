package com.example.exampledbrecycler

import android.app.Application
import android.content.Context

class MyApp: Application() {
    companion object {
        lateinit var appCntx: Context
            private set
    }

    override fun onCreate() {
        super.onCreate()
        appCntx = applicationContext
    }
}