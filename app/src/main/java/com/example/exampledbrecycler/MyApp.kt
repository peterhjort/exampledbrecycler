package com.example.exampledbrecycler

import android.app.Application
import android.content.Context

class MyApp: Application() {
    override fun onCreate() {
        super.onCreate()
        appContext = this
    }

    companion object {
        lateinit var appContext: Context
    }
}
