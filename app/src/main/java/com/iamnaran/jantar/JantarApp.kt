package com.iamnaran.jantar

import androidx.multidex.MultiDexApplication
import com.iamnaran.common.AppLog

class JantarApp : MultiDexApplication() {
    override fun onCreate() {
        super.onCreate()
        AppLog.init()
    }
}