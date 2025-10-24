package com.losmoviles.features.favorites.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import com.losmoviles.shared.ui.screens.CustomScreen
import org.jetbrains.compose.ui.tooling.preview.Preview

// Paleta simple (Rick & Morty vibes)
private val PortalGreen = Color(0xFF97CE4C)
private val RickCyan    = Color(0xFF00B5CC)
private val MortyYellow = Color(0xFFF9D648)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FavoritesScreen() {
    CustomScreen(
        title = "Favorites",
        onBack = null,
        actions = {
            IconButton(onClick = { /* no-op (visual) */ }) {
                Icon(Icons.Filled.Search, contentDescription = "Search")
            }
        }
        // bottomBar: colócalo desde el contenedor si usas tabs globales
    ) {
        val mock = remember {
            listOf(
                FavCardVM("Rick Sanchez", "Earth (C-137)", PortalGreen),
                FavCardVM("Morty Smith",  "Earth (C-137)", RickCyan),
                FavCardVM("Mr. Meeseeks", "Unknown",       MortyYellow),
                FavCardVM("Birdperson",   "Bird World",     RickCyan),
            )
        }

        if (mock.isEmpty()) {
            EmptyState()
        } else {
            FavoritesGrid(items = mock)
        }
    }
}

@Composable
private fun EmptyState() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Surface(
            shape = CircleShape,
            color = MaterialTheme.colorScheme.secondaryContainer
        ) {
            Icon(
                imageVector = Icons.Filled.Favorite,
                contentDescription = null,
                tint = MaterialTheme.colorScheme.secondary,
                modifier = Modifier.padding(18.dp)
            )
        }
        Spacer(Modifier.height(16.dp))
        Text(
            text = "No favorites yet",
            style = MaterialTheme.typography.titleMedium.copy(fontWeight = FontWeight.SemiBold)
        )
        Spacer(Modifier.height(4.dp))
        Text(
            text = "Tap the heart on a character to save it here.",
            style = MaterialTheme.typography.bodyMedium,
            color = MaterialTheme.colorScheme.onSurfaceVariant
        )
    }
}

private data class FavCardVM(
    val name: String,
    val subtitle: String, // ubicación / status
    val color: Color
)

@Composable
private fun FavoritesGrid(items: List<FavCardVM>) {
    Column(modifier = Modifier.fillMaxSize()) {
        // Header opcional
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

@Composable
private fun FavCard(vm: FavCardVM) {
    ElevatedCard(
        shape = RoundedCornerShape(18.dp),
        onClick = { /* no-op (visual) */ }
    ) {
        // Placeholder “imagen”
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(120.dp)
                .background(
                    Brush.linearGradient(
                        listOf(vm.color, vm.color.copy(alpha = 0.6f))
                    )
                )
        ) {
            // Heart fijo (ya que estamos en favoritos)
            Surface(
                color = Color.White.copy(alpha = 0.9f),
                shape = CircleShape,
                tonalElevation = 1.dp,
                modifier = Modifier
                    .padding(8.dp)
                    .size(32.dp)
                    .align(Alignment.TopEnd)
            ) {
                Icon(
                    imageVector = Icons.Filled.Favorite,
                    contentDescription = null,
                    tint = MaterialTheme.colorScheme.error,
                    modifier = Modifier.padding(6.dp)
                )
            }
        }

        Column(Modifier.padding(12.dp)) {
            Text(
                text = vm.name,
                style = MaterialTheme.typography.titleSmall,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis
            )
            Spacer(Modifier.height(2.dp))
            Text(
                text = vm.subtitle,
                style = MaterialTheme.typography.bodySmall,
                color = MaterialTheme.colorScheme.onSurfaceVariant,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis
            )
        }
    }
}

// Preview (Android)
@Preview(showBackground = true)
@Composable
private fun FavoritesPreview() {
    MaterialTheme { FavoritesScreen() }
}
