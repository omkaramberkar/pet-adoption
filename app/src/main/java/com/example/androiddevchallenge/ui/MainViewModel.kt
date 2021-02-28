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
package com.example.androiddevchallenge.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.androiddevchallenge.data.api.PetfinderAuthenticationInterceptor
import com.example.androiddevchallenge.data.model.Animal
import com.example.androiddevchallenge.domain.PetfinderRepository
import com.example.androiddevchallenge.domain.TokenManagerRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val tokenManagerRepository: TokenManagerRepository,
    private val petfinderRepository: PetfinderRepository,
    private val interceptor: PetfinderAuthenticationInterceptor
) : ViewModel() {

    companion object {
        private const val LOCATION = "New York City, NY"

        const val ANIMAL_TYPE_DOG = "Dog"
        const val ANIMAL_TYPE_CAT = "Cat"
        val ANIMAL_TYPES =
            listOf(ANIMAL_TYPE_DOG, ANIMAL_TYPE_CAT)
    }

    private val _dogs = MutableLiveData<List<Animal>>(emptyList())
    val dogs: LiveData<List<Animal>> = _dogs

    private val _cats = MutableLiveData<List<Animal>>(emptyList())
    val cats: LiveData<List<Animal>> = _cats

    init {
        refreshAccessToken()
    }

    private fun refreshAccessToken() = viewModelScope.launch {
        tokenManagerRepository.refreshAccessToken().collect { accessToken ->
            if (accessToken.isNotBlank()) {
                interceptor.updateAccessToken(accessToken)
                ANIMAL_TYPES.forEach { type -> fetchAnimals(type, LOCATION) }
            }
        }
    }

    private fun fetchAnimalTypes() = viewModelScope.launch {
        petfinderRepository.getAnimalTypes()
    }

    private fun fetchAnimalBreedsForType(type: String) = viewModelScope.launch {
        petfinderRepository.getAnimalBreedsForType(type)
    }

    private fun fetchAnimals(type: String, location: String) = viewModelScope.launch {
        petfinderRepository.getAnimals(type = type, location = location).collect { animals ->
            when (type) {
                ANIMAL_TYPE_DOG -> _dogs.value = animals
                ANIMAL_TYPE_CAT -> _cats.value = animals
            }
        }
    }
}
