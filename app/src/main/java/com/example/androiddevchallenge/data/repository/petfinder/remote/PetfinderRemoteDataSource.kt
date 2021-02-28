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
package com.example.androiddevchallenge.data.repository.petfinder.remote

import com.example.androiddevchallenge.data.api.Petfinder
import com.example.androiddevchallenge.data.model.Age
import com.example.androiddevchallenge.data.model.Coat
import com.example.androiddevchallenge.data.model.Gender
import com.example.androiddevchallenge.data.model.Size
import com.example.androiddevchallenge.data.model.Sort
import com.example.androiddevchallenge.data.model.Status
import com.example.androiddevchallenge.data.utils.AppCoroutineDispatchers
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import timber.log.Timber
import javax.inject.Inject

class PetfinderRemoteDataSource @Inject constructor(
    private val petfinder: Petfinder,
    private val dispatchers: AppCoroutineDispatchers
) {

    suspend fun getAnimalTypes() = flow {
        val response = petfinder.getAnimalTypes()
        if (response.isSuccessful) {
            emit(response.body())
        } else {
            emit(null)
        }
    }.catch {
        Timber.e(it)
        emit(null)
    }.flowOn(dispatchers.io)

    suspend fun getAnimalBreedsForType(type: String) = flow {
        val response = petfinder.getAnimalBreedsForType(type)
        if (response.isSuccessful) {
            emit(response.body())
        } else {
            emit(null)
        }
    }.catch {
        Timber.e(it)
        emit(null)
    }.flowOn(dispatchers.io)

    suspend fun getAnimals(
        type: String? = null,
        breed: String? = null,
        @Size size: String? = null,
        @Gender gender: String? = null,
        @Age age: String? = null,
        color: String? = null,
        @Coat coat: String? = null,
        @Status status: String? = null,
        name: String? = null,
        organization: String? = null,
        goodWithChildren: Boolean? = null,
        goodWithDogs: Boolean? = null,
        goodWithCats: Boolean? = null,
        houseTrained: Boolean? = null,
        declawed: Boolean? = null,
        specialNeeds: Boolean? = null,
        location: String? = null,
        distance: Int? = null,
        before: String? = null,
        after: String? = null,
        @Sort sort: String? = null,
        page: Int? = null,
        limit: Int? = null
    ) = flow {
        val response = petfinder.getAnimals(
            type,
            breed,
            size,
            gender,
            age,
            color,
            coat,
            status,
            name,
            organization,
            goodWithChildren,
            goodWithDogs,
            goodWithCats,
            houseTrained,
            declawed,
            specialNeeds,
            location,
            distance,
            before,
            after,
            sort,
            page,
            limit
        )
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
