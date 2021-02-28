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

class Breeds(
    @Json(name = "name") val name: String? = null,
    @Json(name = "primary") val primary: String? = null,
    @Json(name = "secondary") val secondary: String? = null,
    @Json(name = "mixed") val mixed: Boolean? = null,
    @Json(name = "unknown") val unknown: Boolean? = null,
    @Json(name = "_links") val links: Links? = null
)
