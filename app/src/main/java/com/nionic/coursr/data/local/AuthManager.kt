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
    private val EMAIL_KEY = stringPreferencesKey("email")
    val userEmail: Flow<String?> = dataStore.data.map { prefs ->
        prefs[EMAIL_KEY]
    }

    suspend fun saveEmail(email: String) {
        dataStore.edit { prefs ->
            prefs[EMAIL_KEY] = email
        }
    }

    suspend fun clearEmail() {
        dataStore.edit { prefs ->
            prefs.remove(EMAIL_KEY)
        }
    }
}