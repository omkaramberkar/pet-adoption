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

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.navArgument
import androidx.navigation.compose.navigate
import com.example.androiddevchallenge.ui.details.DetailsScreen
import com.example.androiddevchallenge.ui.overview.OverviewScreen

const val PET_ID = "petId"

const val OVERVIEW_SCREEN_ROUTE = "overview"
const val DETAILS_SCREEN_ROUTE = "details/{$PET_ID}"

sealed class ScreenRoute(val route: String) {
    object OverviewScreenRoute : ScreenRoute(OVERVIEW_SCREEN_ROUTE)
    object DetailsScreenRoute : ScreenRoute(DETAILS_SCREEN_ROUTE)
}

@ExperimentalFoundationApi
@Composable
fun PetAdoptionNavigation(
    navController: NavHostController,
    mainViewModel: MainViewModel
) {
    NavHost(
        navController = navController,
        startDestination = ScreenRoute.OverviewScreenRoute.route
    ) {
        composable(route = ScreenRoute.OverviewScreenRoute.route) {
            OverviewScreen(mainViewModel) {
                navController.navigate(
                    ScreenRoute.DetailsScreenRoute.route.replace(
                        "{$PET_ID}",
                        it.toString()
                    )
                )
            }
        }
        composable(
            route = ScreenRoute.DetailsScreenRoute.route,
            arguments = listOf(navArgument(name = PET_ID) { type = NavType.StringType })
        ) { backStackEntry ->
            DetailsScreen(backStackEntry.arguments?.getString(PET_ID), mainViewModel) {
                navController.popBackStack()
            }
        }
    }
}
