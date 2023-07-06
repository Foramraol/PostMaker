package com.ocean.postermaker

import android.content.Context
import androidx.multidex.MultiDex
import androidx.multidex.MultiDexApplication
import com.ocean.postermaker.AdManager.AppOpenManager
import com.oceanmtech.dmt.DI.AppModule
import com.oceanmtech.dmt.DI.NetworkModule
import com.oceanmtech.dmt.DI.ViewModelModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

class OceanmtechPosterMakerApp : MultiDexApplication() {

    companion object {
        var context: Context? = null
    }

    override fun attachBaseContext(base: Context?) {
        super.attachBaseContext(base)
        MultiDex.install(this)
    }

    override fun onCreate() {
        super.onCreate()
        startKoinDi()
        context = applicationContext
        AppOpenManager(this)
    }

    private fun startKoinDi() {
        startKoin {
            androidLogger()
            androidContext(this@OceanmtechPosterMakerApp)
            modules(
                listOf(
                    AppModule,
                    NetworkModule,
                    ViewModelModule)
            )
        }
    }
}