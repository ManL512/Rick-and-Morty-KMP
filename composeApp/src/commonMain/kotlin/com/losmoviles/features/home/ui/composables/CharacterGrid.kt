package com.losmoviles.features.home.ui.composables

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.graphics.Color

fun mockCharacters() = listOf(
    CardVM("Rick Sanchez", "$200", Color(0xFF97CE4C), true),
    CardVM("Morty Smith", "$180", Color(0xFF00B5CC), false),
    CardVM("Summer Smith", "$150", Color(0xFFF9D648), false),
    CardVM("Mr. Meeseeks", "$99", Color(0xFF00B5CC), true)
)

@Composable
fun CharacterGrid(items: List<CardVM>) {
    LazyVerticalGrid(
        columns = GridCells.Fixed(2),
        modifier = Modifier.fillMaxWidth().heightIn(min = 200.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp),
        horizontalArrangement = Arrangement.spacedBy(12.dp),
        contentPadding = PaddingValues(bottom = 100.dp)
    ) {
        items(items) { CharacterCard(it) }
    }
}
