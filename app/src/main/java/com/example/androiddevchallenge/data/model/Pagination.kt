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

data class Pagination(
    @Json(name = "count_per_page") val countPerPage: Int? = null,
    @Json(name = "total_count") val totalCount: Int? = null,
    @Json(name = "current_page") val currentPage: Int? = null,
    @Json(name = "total_pages") val totalPages: Int? = null,
    @Json(name = "_links") val links: Links? = null
)
