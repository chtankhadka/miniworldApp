package com.chtan.miniworld

import android.app.Application
import com.chtan.miniworld.di.initKoin
import com.chtan.miniworld.di.initKoinAndroid
import io.ktor.http.ContentType
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

class MainApplication: Application() {

    override fun onCreate() {
        super.onCreate()
        initKoinAndroid(this@MainApplication)
    }



}