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
package com.example.androiddevchallenge.domain

import com.example.androiddevchallenge.data.model.Age
import com.example.androiddevchallenge.data.model.Animal
import com.example.androiddevchallenge.data.model.Breeds
import com.example.androiddevchallenge.data.model.Coat
import com.example.androiddevchallenge.data.model.Gender
import com.example.androiddevchallenge.data.model.Size
import com.example.androiddevchallenge.data.model.Sort
import com.example.androiddevchallenge.data.model.Status
import com.example.androiddevchallenge.data.model.Type
import kotlinx.coroutines.flow.Flow

interface PetfinderRepository {
    suspend fun getAnimalTypes(): Flow<List<Type>>
    suspend fun getAnimalBreedsForType(type: String): Flow<List<Breeds>>
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
    ): Flow<List<Animal>>
}
