package com.chtan.miniworld.di

import com.chtan.miniworld.data.datasource.local.DataStoreFactory
import org.koin.dsl.module

val jvmModules = module {
    single { DataStoreFactory().createDataStore() }

}

fun initKoinJVM() = initKoin(additionalModules = listOf(jvmModules))