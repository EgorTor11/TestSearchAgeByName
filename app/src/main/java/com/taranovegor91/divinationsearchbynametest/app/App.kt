package com.taranovegor91.divinationsearchbynametest.app

import android.app.Application
import com.taranovegor91.divinationsearchbynametest.data.MainDb


lateinit var mainDb: MainDb
class App: Application() {
    override fun onCreate() {
        super.onCreate()
        mainDb = MainDb.getDb(applicationContext)
    }
}