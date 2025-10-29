package com.losmoviles.features.favorites.ui.composables


import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp


import androidx.compose.ui.graphics.Color

// Rick & Morty vibes
val PortalGreen = Color(0xFF97CE4C)
val RickCyan    = Color(0xFF00B5CC)
val MortyYellow = Color(0xFFF9D648)

@Composable
fun FavoritesGrid(items: List<FavCardVM>) {
    Column(modifier = Modifier.fillMaxSize()) {
        Text(
            text = "Your liked characters",
            style = MaterialTheme.typography.titleMedium,
            modifier = Modifier.padding(horizontal = 16.dp, vertical = 12.dp)
        )

        LazyVerticalGrid(
            columns = GridCells.Fixed(2),
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 12.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp),
            horizontalArrangement = Arrangement.spacedBy(12.dp),
            contentPadding = PaddingValues(bottom = 24.dp)
        ) {
            items(items) { FavCard(it) }
        }
    }
}

// Mock helper (visual only)
fun mockFavorites(): List<FavCardVM> = listOf(
    FavCardVM("Rick Sanchez", "Earth (C-137)", PortalGreen),
    FavCardVM("Morty Smith",  "Earth (C-137)", RickCyan),
    FavCardVM("Mr. Meeseeks", "Unknown",       MortyYellow),
    FavCardVM("Birdperson",   "Bird World",    RickCyan),
)
