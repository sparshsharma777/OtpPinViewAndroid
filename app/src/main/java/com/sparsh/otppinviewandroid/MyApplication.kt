package com.sparsh.otppinviewandroid

import android.app.Application
import android.util.Log

class MyApplication :Application() {

    companion object {
        lateinit var instance: MyApplication
    }

    init {
        Log.d("Application", "initialized")
    }

    override fun onCreate() {
        super.onCreate()
        instance = this

    }
}