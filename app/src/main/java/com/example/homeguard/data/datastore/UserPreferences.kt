package com.example.homeguard.data.datastore

import android.content.Context
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

private val Context.dataStore by preferencesDataStore(name = "user_preferences")

class UserPreferences(private val context: Context) {
    companion object {
        val NAME_KEY = stringPreferencesKey("name")
        val ADDRESS_KEY = stringPreferencesKey("address")
    }

    val userName: Flow<String?> = context.dataStore.data.map { preferences ->
        preferences[NAME_KEY]
    }

    val userAddress: Flow<String?> = context.dataStore.data.map { preferences ->
        preferences[ADDRESS_KEY]
    }

    suspend fun saveUser(name: String, address: String) {
        context.dataStore.edit { preferences ->
            preferences[NAME_KEY] = name
            preferences[ADDRESS_KEY] = address
        }
    }
}
