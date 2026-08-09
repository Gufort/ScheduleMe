package com.example.scheduleme.data

import android.content.Context
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.preferencesDataStore
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject
import javax.inject.Singleton

private val Context.dataStore by preferencesDataStore(
    name = "settings"
)

@Singleton
class SettingsRepository @Inject constructor(
    @ApplicationContext private val context: Context
){
    private val darkThemeKey = booleanPreferencesKey("dark_theme")

    val darkTheme: Flow<Boolean> =
        context.dataStore.data.map { preferences ->
            preferences[darkThemeKey]  ?: false
        }

    suspend fun setDarkTheme(value: Boolean){
        context.dataStore.edit { preferences ->
            preferences[darkThemeKey] = value
        }
    }
}
