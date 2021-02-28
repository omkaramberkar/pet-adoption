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

import androidx.annotation.StringDef
import com.example.androiddevchallenge.data.model.Size.Companion.LARGE
import com.example.androiddevchallenge.data.model.Size.Companion.MEDIUM
import com.example.androiddevchallenge.data.model.Size.Companion.SMALL
import com.example.androiddevchallenge.data.model.Size.Companion.X_LARGE

@StringDef(SMALL, MEDIUM, LARGE, X_LARGE)
annotation class Size {
    companion object {
        const val SMALL = "small"
        const val MEDIUM = "medium"
        const val LARGE = "large"
        const val X_LARGE = "xlarge"
    }
}
