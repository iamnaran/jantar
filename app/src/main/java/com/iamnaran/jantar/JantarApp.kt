package com.iamnaran.jantar

import androidx.multidex.MultiDexApplication
import com.iamnaran.common.log.AppLog
import com.iamnaran.jantar.di.appModules
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.GlobalContext.startKoin

class JantarApp : MultiDexApplication() {
    override fun onCreate() {
        super.onCreate()
        AppLog.init()

        startKoin {
            androidLogger()
            androidContext(this@JantarApp)
            modules(appModules)
        }

    }
}