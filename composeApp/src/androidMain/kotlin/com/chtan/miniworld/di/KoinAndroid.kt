package com.chtan.miniworld.di


import android.app.Application
import com.chtan.miniworld.MainApplication
import com.chtan.miniworld.data.datasource.local.DataStoreFactory
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

val androidModules = module {
    single{ DataStoreFactory(this.androidContext()).createDataStore()}


}

fun initKoinAndroid(app: Application) = initKoin(
    appDeclaration = { androidContext(app) },
    additionalModules = listOf(androidModules)
)