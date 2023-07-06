package com.oceanmtech.dmt.DI

import com.oceanmtech.dmt.Data.DataManager
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

val AppModule = module {
    single { DataManager(androidContext()) }
}