package com.chtan.miniworld.di

import com.chtan.miniworld.data.datasource.local.DataStoreFactory
import org.koin.dsl.module

val iosModules = module {
    single { DataStoreFactory().createDataStore() }

}

fun initKoinIOS() = initKoin(additionalModules = listOf(iosModules))