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
package com.example.androiddevchallenge.data.model

import com.squareup.moshi.Json

class Animal(
    @Json(name = "id") val id: Int? = null,
    @Json(name = "organization_id") val organizationId: String? = null,
    @Json(name = "url") val url: String? = null,
    @Json(name = "type") val type: String? = null,
    @Json(name = "species") val species: String? = null,
    @Json(name = "breeds") val breeds: Breeds? = null,
    @Json(name = "colors") val colors: Colors? = null,
    @Json(name = "age") val age: String? = null,
    @Json(name = "gender") val gender: String? = null,
    @Json(name = "size") val size: String? = null,
    @Json(name = "coat") val coat: String? = null,
    @Json(name = "attributes") val attributes: Attributes? = null,
    @Json(name = "environment") val environment: Environment? = null,
    @Json(name = "tags") val tags: List<String>? = null,
    @Json(name = "name") val name: String? = null,
    @Json(name = "description") val description: String? = null,
    @Json(name = "organization_animal_id") val organizationAnimalId: String? = null,
    @Json(name = "photos") val photos: List<Photo>? = null,
    @Json(name = "primary_photo_cropped") val primaryPhotoCropped: Photo? = null,
    @Json(name = "videos") val videos: List<Video>? = null,
    @Json(name = "status") val status: String? = null,
    @Json(name = "status_changed_at") val statusChangedAt: String? = null,
    @Json(name = "published_at") val publishedAt: String? = null,
    @Json(name = "distance") val distance: Float? = null,
    @Json(name = "contact") val contact: Contact? = null,
    @Json(name = "_links") val links: Links? = null,
)
