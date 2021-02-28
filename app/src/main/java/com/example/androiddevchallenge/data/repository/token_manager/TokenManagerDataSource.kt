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
package com.example.androiddevchallenge.data.repository.token_manager

import com.example.androiddevchallenge.data.repository.token_manager.local.TokenManagerLocalDataSource
import com.example.androiddevchallenge.data.repository.token_manager.remote.TokenManagerRemoteDataSource
import com.example.androiddevchallenge.data.utils.AppCoroutineDispatchers
import com.example.androiddevchallenge.domain.TokenManagerRepository
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.channelFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

class TokenManagerDataSource @Inject constructor(
    private val localDataSource: TokenManagerLocalDataSource,
    private val remoteDataSource: TokenManagerRemoteDataSource,
    private val dispatchers: AppCoroutineDispatchers
) : TokenManagerRepository {

    @ExperimentalCoroutinesApi
    override suspend fun refreshAccessToken(): Flow<String> = channelFlow {
        launch(dispatchers.io) {
            localDataSource.getAccessToken().collect { accessToken ->
                send(accessToken)
            }
        }
        remoteDataSource.refreshAccessToken().collect { accessToken ->
            if (accessToken != null) {
                localDataSource.updateAccessToken(accessToken)
            }
        }
    }

    override suspend fun getAccessToken(): Flow<String> {
        return localDataSource.getAccessToken()
    }
}
