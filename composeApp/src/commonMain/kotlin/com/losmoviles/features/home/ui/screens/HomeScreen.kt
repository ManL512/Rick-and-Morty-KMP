package com.losmoviles.features.home.ui.screens

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

// Rick & Morty palette (local to Home). If you’ll reuse it, move to features/home/ui/theme/HomeColors.kt
private val PortalGreen = Color(0xFF97CE4C)
private val RickCyan    = Color(0xFF00B5CC)
private val MortyYellow = Color(0xFFF9D648)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(){
    CustomScreen(
        title = "Rick And Morty",
        onBack = null,
        actions = {
            IconButton(onClick = {/* por ahora solo vista visual*/}){
                Icon(Icons.Filled.Search, contentDescription = "Search")
            }
        }
    ){
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(bottom = 16.dp)
        ){
            HeroHeader()
            Spacer(Modifier.height(16.dp))
            CategoryRow()
            Spacer(Modifier.height(16.dp))
            Text(
                text = "Popular Characters",
                style = MaterialTheme.typography.titleMedium,
                modifier = Modifier.padding(horizontal = 16.dp)
            )
            Spacer(Modifier.height(8.dp))
            val mock = remember {
                listOf(
                    "Rick Sanchez" to "Alive",
                    "Morty Smith" to "Alive",
                    "Summer Smith" to "Alive",
                    "Beth Smith" to "Alive",
                    "Jerry Smith" to "Alive",
                    "Mr. Meeseeks" to "Unknown",
                )
            }
            LazyVerticalGrid(
                columns = GridCells.Fixed(2),
                modifier = Modifier
                    .fillMaxSize()
                    .padding(horizontal = 12.dp),
                verticalArrangement = Arrangement.spacedBy(12.dp),
                horizontalArrangement = Arrangement.spacedBy(12.dp),
                contentPadding = PaddingValues(bottom = 24.dp)
            ) {
                items(mock) { (name, status) ->
                    CharacterCardPlaceholder(name, status)
                }
            }

        }
    }
}


@Composable
private fun HeroHeader() {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(170.dp)
            .padding(horizontal = 16.dp, vertical = 12.dp)
            .clip(RoundedCornerShape(24.dp))
            .background(Brush.linearGradient(listOf(RickCyan, PortalGreen)))
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(20.dp),
            verticalArrangement = Arrangement.SpaceBetween
        ) {
            Column {
                Text(
                    "Explore the Multiverse",
                    style = MaterialTheme.typography.titleLarge.copy(fontWeight = FontWeight.ExtraBold),
                    color = Color(0xFF0B1E1D)
                )
                Text(
                    "Characters · Episodes · Locations",
                    style = MaterialTheme.typography.bodyMedium,
                    color = Color(0xFF0B1E1D).copy(alpha = 0.8f)
                )
            }
            Surface(
                shape = RoundedCornerShape(16.dp),
                color = Color.White.copy(alpha = 0.95f),
                tonalElevation = 2.dp
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(48.dp)
                        .padding(horizontal = 14.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Icon(Icons.Filled.Search, contentDescription = null, tint = Color(0xFF2C3E3B))
                    Spacer(Modifier.width(10.dp))
                    Text(
                        "Search characters…",
                        style = MaterialTheme.typography.bodyMedium,
                        color = Color(0xFF2C3E3B).copy(alpha = 0.7f)
                    )
                }
            }
        }
    }
}

@Composable
private fun CategoryRow() {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp),
        horizontalArrangement = Arrangement.spacedBy(10.dp)
    ) {
        CategoryChip("Characters", PortalGreen)
        CategoryChip("Episodes",   MortyYellow)
        CategoryChip("Locations",  RickCyan)
    }
}

@Composable
private fun CategoryChip(label: String, color: Color) {
    Surface(
        shape = CircleShape,
        color = color,
        contentColor = contentColorFor(color),
        tonalElevation = 1.dp
    ) {
        Text(
            label,
            style = MaterialTheme.typography.labelLarge,
            modifier = Modifier.padding(horizontal = 14.dp, vertical = 10.dp)
        )
    }
}

@Composable
private fun CharacterCardPlaceholder(name: String, status: String) {
    ElevatedCard(shape = RoundedCornerShape(20.dp), onClick = { /* no-op */ }) {
        Column {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(120.dp)
                    .background(Brush.linearGradient(listOf(MortyYellow, RickCyan)))
            )
            Column(Modifier.padding(12.dp)) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Text(
                        name,
                        style = MaterialTheme.typography.titleSmall,
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis,
                        modifier = Modifier.weight(1f)
                    )
                    Icon(
                        imageVector = Icons.Filled.Favorite,
                        contentDescription = null,
                        tint = MaterialTheme.colorScheme.outline,
                        modifier = Modifier.size(18.dp)
                    )
                }
                Spacer(Modifier.height(4.dp))
                Surface(
                    shape = CircleShape,
                    color = when (status.lowercase()) {
                        "alive" -> PortalGreen.copy(alpha = 0.25f)
                        "dead"  -> MaterialTheme.colorScheme.error.copy(alpha = 0.2f)
                        else    -> RickCyan.copy(alpha = 0.25f)
                    }
                ) {
                    Text(
                        status,
                        style = MaterialTheme.typography.labelMedium,
                        color = MaterialTheme.colorScheme.onSurfaceVariant,
                        modifier = Modifier.padding(horizontal = 8.dp, vertical = 4.dp)
                    )
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun HomePreview() {
    MaterialTheme { HomeScreen() }
}