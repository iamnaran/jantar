package com.iamnaran.jantar

import android.content.pm.ApplicationInfo
import android.os.StrictMode
import androidx.multidex.MultiDexApplication
import com.iamnaran.common.log.AppLog
import com.iamnaran.jantar.di.appModules
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.GlobalContext.startKoin

class JantarApp : MultiDexApplication() {
    override fun onCreate() {
        super.onCreate()

        setStrictModePolicy()

        AppLog.init()

        startKoin {
            androidLogger()
            androidContext(this@JantarApp)
            modules(appModules)
        }

    }


    /**
     * Set a thread policy that detects all potential problems on the main thread, such as network
     * and disk access.
     *
     * If a problem is found, the offending call will be logged and the application will be killed.
     */
    private fun setStrictModePolicy() {
        if (isDebuggable()) {
//            enableStrictMode()
        }
    }

    private fun isDebuggable(): Boolean {
        return 0 != applicationInfo.flags and ApplicationInfo.FLAG_DEBUGGABLE
    }
}

fun enableStrictMode() {
    StrictMode.setThreadPolicy(
        StrictMode.ThreadPolicy.Builder()
            .detectDiskReads()
            .detectDiskWrites()
            .detectNetwork() // or .detectAll() for all detectable problems
            .penaltyLog() // Log violations to Logcat
            .penaltyDeath() // Crash the app on violation
            .build()
    )
    StrictMode.setVmPolicy(
        StrictMode.VmPolicy.Builder()
            .detectLeakedSqlLiteObjects()
            .detectLeakedClosableObjects()
            .penaltyLog()
            .penaltyDeath()
            .build()
    )
}