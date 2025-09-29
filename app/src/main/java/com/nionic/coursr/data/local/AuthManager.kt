package com.nionic.coursr.data.local

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

private val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = "auth_prefs")

class AuthManager(context: Context) {

    private val dataStore = context.dataStore
    private val emailKey = stringPreferencesKey("email")
    private val passwordKey = stringPreferencesKey("password")

    val userEmail: Flow<String?> = dataStore.data.map { prefs ->
        prefs[emailKey]
    }
    val isLoggedIn: Flow<Boolean> = dataStore.data.map { prefs ->
        prefs[emailKey] != null && prefs[passwordKey] != null
    }

    suspend fun saveCredential(email: String, password: String) {
        dataStore.edit { prefs ->
            prefs[emailKey] = email
            prefs[passwordKey] = password
        }
    }

    suspend fun clearCredential() {
        dataStore.edit { prefs ->
            prefs.remove(emailKey)
            prefs.remove(passwordKey)
        }
    }
}