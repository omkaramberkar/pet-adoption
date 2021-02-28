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
package com.example.androiddevchallenge.data.repository.token_manager.remote

import com.example.androiddevchallenge.BuildConfig.CLIENT_ID
import com.example.androiddevchallenge.BuildConfig.CLIENT_SECRET
import com.example.androiddevchallenge.data.api.GrantType.Companion.CLIENT_CREDENTIALS
import com.example.androiddevchallenge.data.api.TokenManager
import com.example.androiddevchallenge.data.utils.AppCoroutineDispatchers
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import timber.log.Timber
import javax.inject.Inject

class TokenManagerRemoteDataSource @Inject constructor(
    private val tokenManager: TokenManager,
    private val dispatchers: AppCoroutineDispatchers
) {

    suspend fun refreshAccessToken() = flow {
        Timber.d("Debug: Refreshing accessToken...")
        val response = tokenManager.refreshAccessToken(CLIENT_CREDENTIALS, CLIENT_ID, CLIENT_SECRET)
        if (response.isSuccessful) {
            emit(response.body())
        } else {
            emit(null)
        }
    }.catch {
        Timber.e(it)
        emit(null)
    }.flowOn(dispatchers.io)
}
