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
package com.example.androiddevchallenge.data.api

import com.example.androiddevchallenge.data.model.Age
import com.example.androiddevchallenge.data.model.AnimalBreedsResponse
import com.example.androiddevchallenge.data.model.AnimalTypesResponse
import com.example.androiddevchallenge.data.model.AnimalsResponse
import com.example.androiddevchallenge.data.model.Coat
import com.example.androiddevchallenge.data.model.Gender
import com.example.androiddevchallenge.data.model.Size
import com.example.androiddevchallenge.data.model.Sort
import com.example.androiddevchallenge.data.model.Status
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface Petfinder {

    companion object {
        const val ANIMAL_TYPES = "types"
        const val ANIMAL_BREEDS = "{type}/breeds"
        const val ANIMALS = "animals"
    }

    @GET(ANIMAL_TYPES)
    suspend fun getAnimalTypes(): Response<AnimalTypesResponse>

    @GET(ANIMAL_BREEDS)
    suspend fun getAnimalBreedsForType(
        @Path("type") type: String,
    ): Response<AnimalBreedsResponse>

    @GET(ANIMALS)
    suspend fun getAnimals(
        @Query("type") type: String? = null,
        @Query("breed") breed: String? = null,
        @Query("size") @Size size: String? = null,
        @Query("gender") @Gender gender: String? = null,
        @Query("age") @Age age: String? = null,
        @Query("color") color: String? = null,
        @Query("coat") @Coat coat: String? = null,
        @Query("status") @Status status: String? = null,
        @Query("name") name: String? = null,
        @Query("organization") organization: String? = null,
        @Query("good_with_children") goodWithChildren: Boolean? = null,
        @Query("good_with_dogs") goodWithDogs: Boolean? = null,
        @Query("good_with_cats") goodWithCats: Boolean? = null,
        @Query("house_trained") houseTrained: Boolean? = null,
        @Query("declawed") declawed: Boolean? = null,
        @Query("special_needs") specialNeeds: Boolean? = null,
        @Query("location", encoded = false) location: String? = null,
        @Query("distance") distance: Int? = null,
        @Query("before") before: String? = null,
        @Query("after") after: String? = null,
        @Query("sort") @Sort sort: String? = null,
        @Query("page") page: Int? = null,
        @Query("limit") limit: Int? = null
    ): Response<AnimalsResponse>
}
