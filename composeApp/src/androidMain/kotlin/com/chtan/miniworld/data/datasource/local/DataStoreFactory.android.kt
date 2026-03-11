package com.chtan.miniworld.data.datasource.local

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences

actual class DataStoreFactory(context: Context) {
    val path = context.filesDir.resolve(DATA_STORE_FILE_NAME).absolutePath
    actual fun createDataStore(): DataStore<Preferences> {
        return createDataStore { path }
    }
}