package com.chtan.miniworld.data.datasource.local

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.PreferenceDataStoreFactory
import androidx.datastore.preferences.core.Preferences
import okio.Path.Companion.toPath
import java.io.File

actual class DataStoreFactory {
    actual fun createDataStore(): DataStore<Preferences> {
        val file = File(System.getProperty("java.io.tmpdir"), DATA_STORE_FILE_NAME)
        return PreferenceDataStoreFactory.createWithPath(
            produceFile = { file.absolutePath.toPath() } // ✅ okio.Path conversion
        )
    }
}