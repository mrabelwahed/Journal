package com.loc.newsapp.data

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.preferencesDataStore
import com.loc.newsapp.domain.LocalUserManager
import com.loc.newsapp.util.AppConst
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class LocalUserManagerImpl(private val context: Context): LocalUserManager {
    override suspend fun setOnBoardingVisited(isVisited: Boolean) {
        context.dataStore.edit { settings ->
            settings[AppPrefs.ON_BOARDING_VISITED] = isVisited
        }
    }

    override fun isOnBoardingVisited(): Flow<Boolean> {
        return context.dataStore.data.map { settings ->
            settings[AppPrefs.ON_BOARDING_VISITED] ?: false
        }
    }
}

val Context.dataStore: DataStore<Preferences> by preferencesDataStore(AppConst.prefName)

object AppPrefs {
    val ON_BOARDING_VISITED = booleanPreferencesKey("on_boarding_visited")
}