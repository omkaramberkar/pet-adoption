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
package com.example.androiddevchallenge.ui.overview

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.example.androiddevchallenge.data.model.Animal
import com.example.androiddevchallenge.ui.MainViewModel
import com.example.androiddevchallenge.ui.MainViewModel.Companion.ANIMAL_TYPE_CAT
import com.example.androiddevchallenge.ui.MainViewModel.Companion.ANIMAL_TYPE_DOG
import com.example.androiddevchallenge.ui.theme.secondary1Color
import com.example.androiddevchallenge.ui.theme.secondary1DarkColor
import com.example.androiddevchallenge.ui.theme.secondary2Color
import com.example.androiddevchallenge.ui.theme.secondary2DarkColor
import dev.chrisbanes.accompanist.coil.CoilImage

@ExperimentalFoundationApi
@Composable
fun OverviewScreen(
    mainViewModel: MainViewModel,
    onSnackClick: (Int) -> Unit
) {
    val dogs by mainViewModel.dogs.observeAsState(emptyList())
    val cats by mainViewModel.cats.observeAsState(emptyList())
    OverviewContent(
        Modifier.fillMaxSize(),
        Pair(ANIMAL_TYPE_DOG, dogs),
        Pair(ANIMAL_TYPE_CAT, cats),
        onSnackClick
    )
}

@ExperimentalFoundationApi
@Composable
fun OverviewContent(
    modifier: Modifier,
    dogs: Pair<String, List<Animal>>,
    cats: Pair<String, List<Animal>>,
    onSnackClick: (Int) -> Unit
) {
    Column(modifier = modifier) {
        PetsCollection(
            animalType = dogs.first,
            animals = dogs.second,
            onPetItemClick = onSnackClick
        )
        PetsCollection(
            animalType = cats.first,
            animals = cats.second,
            onPetItemClick = onSnackClick
        )
    }
}

@ExperimentalFoundationApi
@Composable
fun PetsCollection(
    animalType: String,
    animals: List<Animal>,
    onPetItemClick: (Int) -> Unit,
    modifier: Modifier = Modifier
) {
    Column(modifier = modifier) {
        Text(
            text = animalType,
            style = MaterialTheme.typography.h6,
            color = Color.White,
            modifier = Modifier
                .heightIn(min = 56.dp)
                .padding(horizontal = 24.dp, vertical = 4.dp)
                .wrapContentHeight()
        )
        LazyRow(
            contentPadding = PaddingValues(horizontal = 16.dp, vertical = 8.dp),
            horizontalArrangement = Arrangement.spacedBy(space = 4.dp)
        ) {
            items(
                items = animals,
                key = { animal ->
                    animal.id ?: throw IllegalStateException("Animal id is null")
                }
            ) { animal ->
                PetItem(
                    animal = animal,
                    onPetItemClick = onPetItemClick,
                    modifier = Modifier.padding(8.dp)
                )
            }
        }
    }
}

@Composable
fun PetItem(
    animal: Animal,
    gradient: List<Color> = listOf(
        secondary1DarkColor,
        secondary1Color,
        secondary2Color,
        secondary2DarkColor
    ),
    onPetItemClick: (Int) -> Unit,
    modifier: Modifier
) {
    Card(
        elevation = 4.dp,
        shape = RoundedCornerShape(size = 8.dp),
        modifier = modifier
            .size(width = 170.dp, height = 280.dp)
            .padding(bottom = 16.dp)
    ) {
        Column(
            modifier = Modifier
                .clickable(onClick = { onPetItemClick(animal.id ?: 0) })
                .fillMaxSize()
        ) {
            Box(
                modifier = Modifier
                    .heightIn(160.dp)
                    .fillMaxWidth()
            ) {
                Box(
                    modifier = Modifier
                        .height(height = 120.dp)
                        .fillMaxWidth()
                        .background(brush = Brush.horizontalGradient(gradient))
                )
                val photos = animal.photos?.firstOrNull()
                val imageUrl = photos?.small ?: photos?.medium ?: photos?.large ?: photos?.full
                PetImage(
                    imageUrl = imageUrl ?: "",
                    contentDescription = null,
                    modifier = Modifier
                        .size(140.dp)
                        .align(Alignment.BottomCenter)
                )
            }
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = animal.name ?: "",
                maxLines = 1,
                overflow = TextOverflow.Ellipsis,
                style = MaterialTheme.typography.h6,
                color = secondary1Color,
                modifier = Modifier.padding(horizontal = 16.dp)
            )
            Spacer(modifier = Modifier.height(4.dp))
            Text(
                text = animal.tags?.joinToString(" | ") ?: "",
                maxLines = 2,
                overflow = TextOverflow.Ellipsis,
                style = MaterialTheme.typography.body1,
                color = secondary2Color,
                modifier = Modifier.padding(horizontal = 16.dp)
            )
        }
    }
}

@Composable
fun PetImage(
    imageUrl: String,
    contentDescription: String?,
    modifier: Modifier = Modifier,
    elevation: Dp = 0.dp
) {
    Surface(
        color = Color.LightGray,
        elevation = elevation,
        shape = CircleShape,
        modifier = modifier
    ) {
        CoilImage(
            data = imageUrl,
            contentDescription = contentDescription,
            contentScale = ContentScale.Crop,
            modifier = Modifier.fillMaxSize()
        )
    }
}
