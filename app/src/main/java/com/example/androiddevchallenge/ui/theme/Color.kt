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
package com.example.androiddevchallenge.ui.theme

import androidx.compose.material.darkColors
import androidx.compose.material.lightColors
import androidx.compose.ui.graphics.Color

val primaryColor = Color(0xFFd9a5b3)
val primaryLightColor = Color(0xFFffd7e5)
val primaryDarkColor = Color(0xFFa77683)
val primaryTextColor = Color(0xFF000000)

val secondary1Color = Color(0xFF1868ae)
val secondary1LightColor = Color(0xFF5b95e0)
val secondary1DarkColor = Color(0xFF003e7e)
val secondary1TextColor = Color(0xFFffffff)

val secondary2Color = Color(0xFFc6d7eb)
val secondary2LightColor = Color(0xFFf9ffff)
val secondary2DarkColor = Color(0xFF95a6b9)
val secondary2TextColor = Color(0xFF000000)

val red700 = Color(0xFFd50000)
val red200 = Color(0xFFff5252)

val DarkColorPalette = darkColors(
    primary = primaryColor,
    primaryVariant = primaryDarkColor,
    onPrimary = primaryTextColor,

    secondary = secondary1Color,
    secondaryVariant = secondary1DarkColor,
    onSecondary = secondary1TextColor,

    onBackground = Color.White,
    onSurface = Color.White,

    error = red200,
    onError = Color.Black
)

val LightColorPalette = lightColors(
    primary = primaryLightColor,
    primaryVariant = primaryDarkColor,
    onPrimary = primaryTextColor,

    secondary = secondary1LightColor,
    secondaryVariant = secondary1DarkColor,
    onSecondary = secondary1TextColor,

    onBackground = Color.Black,
    onSurface = Color.Black,

    error = red700,
    onError = Color.White
)
