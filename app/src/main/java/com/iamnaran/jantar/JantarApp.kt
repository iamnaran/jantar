package com.iamnaran.jantar

class JantarApp : MultiDexApplication() {
    override fun onCreate() {
        super.onCreate()
        AppLog.init()
    }
}