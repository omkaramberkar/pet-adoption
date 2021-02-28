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
package com.example.androiddevchallenge.ui.details

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.androiddevchallenge.data.model.Animal
import com.example.androiddevchallenge.ui.MainViewModel
import com.example.androiddevchallenge.ui.overview.PetImage
import com.example.androiddevchallenge.ui.theme.secondary1Color
import com.example.androiddevchallenge.ui.theme.secondary2Color
import dev.chrisbanes.accompanist.insets.statusBarsPadding

@Composable
fun DetailsScreen(string: String?, mainViewModel: MainViewModel, upPress: () -> Unit) {
    val dogs by mainViewModel.dogs.observeAsState(emptyList())
    val cats by mainViewModel.cats.observeAsState(emptyList())
    val animal = dogs.find { it.id == string?.toInt() } ?: cats.find { it.id == string?.toInt() }
    if (animal == null) {
        // upPress()
    } else {
        // Up(upPress)
        Body(animal)
    }
}

@Composable
private fun Up(upPress: () -> Unit) {
    IconButton(
        onClick = upPress,
        modifier = Modifier
            .statusBarsPadding()
            .padding(horizontal = 16.dp, vertical = 10.dp)
            .size(36.dp)
            .background(
                color = Color.Black.copy(alpha = 0.32f),
                shape = CircleShape
            )
    ) {
        Icon(
            imageVector = Icons.Outlined.ArrowBack,
            tint = secondary2Color,
            contentDescription = null
        )
    }
}

@Composable
private fun Body(
    animal: Animal
) {
    Column {
        Spacer(
            modifier = Modifier
                .fillMaxWidth()
                .statusBarsPadding()
        )
        Column(Modifier.fillMaxWidth()) {
            Column {
                PetImage(
                    imageUrl = animal.photos?.firstOrNull()?.small ?: "",
                    contentDescription = null,
                    modifier = Modifier.size(width = 250.dp, height = 250.dp)
                )
                Text(
                    text = animal.name ?: "",
                    style = MaterialTheme.typography.overline,
                    color = secondary2Color,
                    modifier = Modifier.padding(horizontal = 24.dp)
                )
                Spacer(Modifier.height(4.dp))
                val contact = animal.contact
                val address = contact?.address
                Text(
                    text = listOfNotNull(
                        address?.address1,
                        address?.address2,
                        address?.city,
                        address?.state,
                        address?.postcode,
                        address?.country
                    ).joinToString(" | "),
                    style = MaterialTheme.typography.body1,
                    color = secondary2Color,
                    modifier = Modifier.padding(horizontal = 24.dp)
                )
                Spacer(Modifier.height(4.dp))
                Text(
                    text = listOfNotNull(contact?.phone, contact?.email).joinToString(" | "),
                    style = MaterialTheme.typography.overline,
                    color = secondary2Color,
                    modifier = Modifier.padding(horizontal = 24.dp)
                )
                Spacer(Modifier.height(4.dp))
                Text(
                    text = animal.description ?: "",
                    style = MaterialTheme.typography.body1,
                    color = secondary2Color,
                    modifier = Modifier.padding(horizontal = 24.dp)
                )

                Spacer(Modifier.height(16.dp))
            }
        }
    }
}

@Composable
private fun Title(animal: Animal) {
    Column(
        verticalArrangement = Arrangement.Bottom,
        modifier = Modifier
            .heightIn(min = 128.dp)
            .statusBarsPadding()
    ) {
        PetImage(
            imageUrl = animal.photos?.firstOrNull()?.small ?: "",
            contentDescription = null,
            modifier = Modifier.fillMaxSize()
        )
        Spacer(Modifier.height(16.dp))
        Text(
            text = animal.name ?: "",
            style = MaterialTheme.typography.h4,
            color = secondary2Color,
            modifier = Modifier.padding(horizontal = 24.dp)
        )
        Text(
            text = animal.tags?.plus(animal.coat)?.plus(animal.gender)?.plus(animal.size)
                ?.joinToString(" | ") ?: "",
            style = MaterialTheme.typography.subtitle2,
            fontSize = 20.sp,
            color = secondary1Color,
            modifier = Modifier.padding(horizontal = 24.dp)
        )
        Spacer(Modifier.height(4.dp))
        Text(
            text = animal.description ?: "",
            style = MaterialTheme.typography.h6,
            color = secondary1Color,
            modifier = Modifier.padding(horizontal = 24.dp)
        )

        Spacer(Modifier.height(8.dp))
    }
}
