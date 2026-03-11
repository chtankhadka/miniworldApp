package com.chtan.miniworld.data.datasource.local

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.stringPreferencesKey
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.flow.map

class StoredData(private val preference: DataStore<Preferences>) {


    // Suspend functions to fetch dynamic data
    suspend fun getToken(): String = fetchData("access_token")
    suspend fun getRefreshToken(): String = fetchData("refresh_token")
    suspend fun getUserId(): String = fetchData("id")
    private suspend fun fetchData(key: String): String{
        return preference.data
            .map { preferences -> preferences[stringPreferencesKey(key)] ?: "" }
            .firstOrNull() ?: "" //Avoid blocking and handle empty preferences
    }
}