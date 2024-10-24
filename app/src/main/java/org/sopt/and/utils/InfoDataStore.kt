package org.sopt.and.utils

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class InfoDataStore(private val context: Context) {

    companion object {
        private val Context.infoDataStore: DataStore<Preferences> by preferencesDataStore(name = "info")
        val EMAIL = stringPreferencesKey("user_email")
        val PWD = stringPreferencesKey("user_pwd")
    }

    suspend fun saveEmail(email: String) {
        context.infoDataStore.edit {
            it[EMAIL] = email
        }
    }

    suspend fun savePwd(pwd: String) {
        context.infoDataStore.edit {
            it[PWD] = pwd
        }
    }

    fun getEmail(): Flow<String?> {
        return context.infoDataStore.data.map { prefs ->
            prefs[EMAIL]
        }
    }

    fun getPwd(): Flow<String?> {
        return context.infoDataStore.data.map { prefs ->
            prefs[PWD]
        }
    }

}