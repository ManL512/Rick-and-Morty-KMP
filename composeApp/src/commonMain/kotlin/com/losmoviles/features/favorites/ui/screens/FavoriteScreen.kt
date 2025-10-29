package com.losmoviles.features.favorites.ui.screens

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import com.losmoviles.features.favorites.ui.composables.EmptyState
import com.losmoviles.features.favorites.ui.composables.FavoritesGrid
import com.losmoviles.features.favorites.ui.composables.mockFavorites
import com.losmoviles.shared.ui.screens.CustomScreen

import androidx.compose.ui.graphics.Color

// Rick & Morty vibes
val PortalGreen = Color(0xFF97CE4C)
val RickCyan    = Color(0xFF00B5CC)
val MortyYellow = Color(0xFFF9D648)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FavoritesScreen(onBack: (() -> Unit)? = null) {
    CustomScreen(
        title = "Favorites",
        onBack = onBack,
        actions = {
            IconButton(onClick = { /* no-op (visual) */ }) {
                Icon(Icons.Filled.Search, contentDescription = "Search")
            }
        }
    ) {
        val mock = remember { mockFavorites() }
        if (mock.isEmpty()) {
            EmptyState()
        } else {
            FavoritesGrid(items = mock)
        }
    }
}
