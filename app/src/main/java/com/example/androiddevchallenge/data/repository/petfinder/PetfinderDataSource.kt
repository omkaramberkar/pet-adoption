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
package com.example.androiddevchallenge.data.repository.petfinder

import com.example.androiddevchallenge.data.model.Animal
import com.example.androiddevchallenge.data.model.Breeds
import com.example.androiddevchallenge.data.model.Type
import com.example.androiddevchallenge.data.repository.petfinder.local.PetfinderLocalDataSource
import com.example.androiddevchallenge.data.repository.petfinder.remote.PetfinderRemoteDataSource
import com.example.androiddevchallenge.domain.PetfinderRepository
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.filterNotNull
import kotlinx.coroutines.flow.mapNotNull
import javax.inject.Inject

class PetfinderDataSource @Inject constructor(
    private val localDataSource: PetfinderLocalDataSource,
    private val remoteDataSource: PetfinderRemoteDataSource
) : PetfinderRepository {

    override suspend fun getAnimalTypes(): Flow<List<Type>> {
        return remoteDataSource.getAnimalTypes()
            .filterNotNull()
            .mapNotNull { it.types }
    }

    override suspend fun getAnimalBreedsForType(type: String): Flow<List<Breeds>> {
        return remoteDataSource.getAnimalBreedsForType(type)
            .filterNotNull()
            .mapNotNull { it.breeds }
    }

    @ExperimentalCoroutinesApi
    override suspend fun getAnimals(
        type: String?,
        breed: String?,
        size: String?,
        gender: String?,
        age: String?,
        color: String?,
        coat: String?,
        status: String?,
        name: String?,
        organization: String?,
        goodWithChildren: Boolean?,
        goodWithDogs: Boolean?,
        goodWithCats: Boolean?,
        houseTrained: Boolean?,
        declawed: Boolean?,
        specialNeeds: Boolean?,
        location: String?,
        distance: Int?,
        before: String?,
        after: String?,
        sort: String?,
        page: Int?,
        limit: Int?
    ): Flow<List<Animal>> {
        return remoteDataSource.getAnimals(
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
            .filterNotNull()
            .mapNotNull { it.animals }
    }
}
