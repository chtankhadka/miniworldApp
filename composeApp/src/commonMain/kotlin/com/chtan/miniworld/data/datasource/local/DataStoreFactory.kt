package com.chtan.miniworld.data.datasource.local

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import org.koin.dsl.module

expect class DataStoreFactory {
    fun createDataStore(): DataStore<Preferences>
}