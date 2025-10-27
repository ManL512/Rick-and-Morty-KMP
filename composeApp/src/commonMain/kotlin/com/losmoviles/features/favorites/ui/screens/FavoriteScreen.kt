package com.losmoviles.features.favorites.ui.screens

import com.losmoviles.shared.ui.screens.CustomScreen

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.graphics.Color
import com.losmoviles.features.favorites.ui.composables.FavoritesEmptyState
import com.losmoviles.features.favorites.ui.composables.FavoritesGrid
import org.jetbrains.compose.ui.tooling.preview.Preview

// Paleta simple (Rick & Morty vibes)
private val PortalGreen = Color(0xFF97CE4C)
private val RickCyan    = Color(0xFF00B5CC)
private val MortyYellow = Color(0xFFF9D648)

data class FavCardVM(
    val name: String,
    val subtitle: String,
    val color: Color
)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FavoritesScreen(onBack: (() -> Unit)? = null) {
    CustomScreen(
        title = "Favorites",
        onBack = onBack,
        actions = {
            IconButton(onClick = { /* no-op */ }) {
                Icon(Icons.Filled.Search, contentDescription = "Search")
            }
        }
    ) {
        val mock = remember {
            listOf(
                FavCardVM("Rick Sanchez", "Earth (C-137)", PortalGreen),
                FavCardVM("Morty Smith",  "Earth (C-137)", RickCyan),
                FavCardVM("Mr. Meeseeks", "Unknown",       MortyYellow),
                FavCardVM("Birdperson",   "Bird World",    RickCyan),
            )
        }

        if (mock.isEmpty()) {
            FavoritesEmptyState()
        } else {
            FavoritesGrid(items = mock)
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun FavoritesPreview() {
    MaterialTheme { FavoritesScreen() }
}
