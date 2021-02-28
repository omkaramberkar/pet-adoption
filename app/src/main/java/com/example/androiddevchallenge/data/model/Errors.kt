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

sealed class Errors(code: Int) {
    data class AccessDeniedDueToInvalidToken(val code: Int = 401) : Errors(code)
    data class AccessDeniedDueToInsufficientAccess(val code: Int = 403) : Errors(code)
    data class ResourceNotFound(val code: Int = 404) : Errors(code)
    data class RequestRanIntoUnexpectedError(val code: Int = 500) : Errors(code)
    data class RequestHasMissingParameters(val code: Int = 1) : Errors(code)
    data class RequestContainsInvalidParameters(val code: Int = 2) : Errors(code)
}
