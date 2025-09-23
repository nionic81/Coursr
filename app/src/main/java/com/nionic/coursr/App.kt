package com.nionic.coursr

import android.app.Application
import com.nionic.coursr.data.local.db.DbProvider
import com.nionic.coursr.di.appModules
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

class App : Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidLogger()
            androidContext(this@App)
            modules(appModules)
        }
        DbProvider.getDB(this)
    }
}