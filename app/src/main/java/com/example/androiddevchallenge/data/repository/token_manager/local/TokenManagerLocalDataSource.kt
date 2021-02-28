/*
 * Copyright 2021 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.example.androiddevchallenge.data.repository.token_manager.local

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import com.example.androiddevchallenge.data.model.AccessToken
import com.example.androiddevchallenge.data.repository.token_manager.local.TokenManagerLocalDataSource.PreferenceKeys.ACCESS_TOKEN
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map
import timber.log.Timber
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class TokenManagerLocalDataSource @Inject constructor(
    @ApplicationContext private val context: Context
) {

    companion object {
        const val PREFERENCES_NAME = "token_manager"
    }

    private object PreferenceKeys {
        val ACCESS_TOKEN = stringPreferencesKey("access_token")
    }

    private val Context.dataStore: DataStore<Preferences> by preferencesDataStore(PREFERENCES_NAME)

    suspend fun updateAccessToken(accessToken: AccessToken) {
        require(!accessToken.accessToken.isNullOrBlank()) { "Access token is null or not available." }
        context.dataStore.edit { preferences ->
            preferences[ACCESS_TOKEN] = accessToken.accessToken
        }
    }

    fun getAccessToken() = context.dataStore.data.map { preferences ->
        preferences[ACCESS_TOKEN] ?: ""
    }.catch { Timber.e(it) }
}
