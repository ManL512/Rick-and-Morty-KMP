package com.losmoviles.features.home.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material.icons.filled.FilterList
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material.icons.outlined.GridView
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

// Rick & Morty core palette (3 main colors)
private val PortalGreen = Color(0xFF97CE4C)
private val RickCyan    = Color(0xFF00B5CC)
private val MortyYellow = Color(0xFFF9D648)
private val AvatarBorder = Color(0xFFE6E8EC)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen() {
    CustomScreen(
        title = "",
        onBack = null,
        actions = {
            IconButton(onClick = { /* no-op */ }) {
                Icon(Icons.Filled.Menu, contentDescription = "Menu")
            }
        },
        bottomBar = {
            HomeBottomBar(
                selected = HomeTab.HOME,
                onSelect = { /* no-op (visual only) */ }
            )
        }
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 16.dp)
        ) {
            Spacer(Modifier.height(8.dp))
            WelcomeRow(title = "Welcome back,", name = "Sara Smith")

            Spacer(Modifier.height(16.dp))
            SearchRow()

            Spacer(Modifier.height(24.dp))
            CategoryRow(
                items = listOf(
                    "Humans" to MortyYellow,
                    "Aliens" to RickCyan,
                    "Robots" to PortalGreen,
                    "More"   to Color(0xFFD9D9D9)
                )
            )

            Spacer(Modifier.height(20.dp))
            SectionHeader(title = "For You", action = "See all")

            Spacer(Modifier.height(8.dp))
            CharacterGrid(
                items = remember {
                    listOf(
                        CardVM("Rick Sanchez", "$200", PortalGreen, liked = true),
                        CardVM("Morty Smith", "$180", RickCyan, liked = false),
                        CardVM("Summer Smith", "$150", MortyYellow, liked = false),
                        CardVM("Mr. Meeseeks", "$99", RickCyan, liked = true)
                    )
                }
            )
            Spacer(Modifier.height(16.dp))
        }
    }
}

// ── pieces ────────────────────────────────────────────────────────────────────

@Composable
private fun WelcomeRow(title: String, name: String) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Column(modifier = Modifier.weight(1f)) {
            Text(
                text = title,
                style = MaterialTheme.typography.labelLarge,
                color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.7f)
            )
            Text(
                text = name,
                style = MaterialTheme.typography.headlineSmall.copy(fontWeight = FontWeight.Bold)
            )
        }
        Box(
            modifier = Modifier
                .size(36.dp)
                .clip(CircleShape)
                .background(Brush.linearGradient(listOf(MortyYellow, RickCyan)))
                .border(1.dp, AvatarBorder, CircleShape)
        )
    }
}

@Composable
private fun SearchRow() {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(48.dp)
            .clip(RoundedCornerShape(16.dp))
            .background(MaterialTheme.colorScheme.surfaceVariant.copy(alpha = 0.6f))
            .padding(horizontal = 12.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(Icons.Filled.Search, contentDescription = "Search")
        Spacer(Modifier.width(10.dp))
        Text(
            text = "Search here",
            color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.6f),
            style = MaterialTheme.typography.bodyMedium
        )
        Spacer(Modifier.weight(1f))
        Surface(shape = CircleShape, color = MaterialTheme.colorScheme.surface, tonalElevation = 1.dp) {
            IconButton(onClick = { /* no-op */ }, modifier = Modifier.size(40.dp)) {
                Icon(Icons.Filled.FilterList, contentDescription = "Filter")
            }
        }
    }
}

@Composable
private fun CategoryRow(items: List<Pair<String, Color>>) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        items.take(4).forEach { (label, color) ->
            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                Box(
                    modifier = Modifier
                        .size(60.dp)
                        .clip(CircleShape)
                        .background(color)
                )
                Spacer(Modifier.height(6.dp))
                Text(
                    text = label,
                    style = MaterialTheme.typography.labelLarge,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )
            }
        }
    }
}

@Composable
private fun SectionHeader(title: String, action: String) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = title,
            style = MaterialTheme.typography.titleMedium.copy(fontWeight = FontWeight.SemiBold),
            modifier = Modifier.weight(1f)
        )
        Text(
            text = action,
            style = MaterialTheme.typography.labelLarge,
            color = MaterialTheme.colorScheme.primary
        )
    }
}

private data class CardVM(
    val name: String,
    val price: String,
    val color: Color,
    val liked: Boolean
)

@Composable
private fun CharacterGrid(items: List<CardVM>) {
    LazyVerticalGrid(
        columns = GridCells.Fixed(2),
        modifier = Modifier
            .fillMaxWidth()
            .heightIn(min = 200.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp),
        horizontalArrangement = Arrangement.spacedBy(12.dp),
        contentPadding = PaddingValues(bottom = 100.dp)
    ) {
        items(items) { vm -> CharacterCard(vm) }
    }
}

@Composable
private fun CharacterCard(vm: CardVM) {
    ElevatedCard(shape = RoundedCornerShape(18.dp), onClick = { /* no-op */ }) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(120.dp)
                .background(vm.color)
        ) {
            Surface(
                color = Color.White.copy(alpha = 0.85f),
                shape = CircleShape,
                tonalElevation = 1.dp,
                modifier = Modifier
                    .padding(8.dp)
                    .size(32.dp)
                    .align(Alignment.TopEnd)
            ) {
                Icon(
                    imageVector = if (vm.liked) Icons.Filled.Favorite else Icons.Filled.FavoriteBorder,
                    contentDescription = null,
                    tint = if (vm.liked) MaterialTheme.colorScheme.error
                    else MaterialTheme.colorScheme.onSurfaceVariant,
                    modifier = Modifier.padding(6.dp)
                )
            }
        }
        Column(Modifier.padding(12.dp)) {
            Text(vm.name, style = MaterialTheme.typography.titleSmall, maxLines = 1, overflow = TextOverflow.Ellipsis)
            Spacer(Modifier.height(2.dp))
            Text("New-York, USA", style = MaterialTheme.typography.bodySmall, color = MaterialTheme.colorScheme.onSurfaceVariant)
            Spacer(Modifier.height(6.dp))
            Text(vm.price, style = MaterialTheme.typography.labelLarge.copy(fontWeight = FontWeight.SemiBold))
        }
    }
}

// Bottom bar (visual only)
private enum class HomeTab { HOME, FAVORITES, CATALOG, PROFILE, SETTINGS }

@Composable
private fun HomeBottomBar(selected: HomeTab, onSelect: (HomeTab) -> Unit) {
    NavigationBar {
        NavigationBarItem(
            selected = selected == HomeTab.HOME,
            onClick = { onSelect(HomeTab.HOME) },
            icon = { Icon(Icons.Filled.Home, contentDescription = "Home") },
            label = { Text("Home") },
            alwaysShowLabel = false
        )
        NavigationBarItem(
            selected = selected == HomeTab.FAVORITES,
            onClick = { onSelect(HomeTab.FAVORITES) },
            icon = { Icon(Icons.Filled.Favorite, contentDescription = "Favorites") },
            label = { Text("Favorites") },
            alwaysShowLabel = false
        )
        NavigationBarItem(
            selected = selected == HomeTab.CATALOG,
            onClick = { onSelect(HomeTab.CATALOG) },
            icon = { Icon(Icons.Outlined.GridView, contentDescription = "Catalog") },
            label = { Text("Catalog") },
            alwaysShowLabel = false
        )
        NavigationBarItem(
            selected = selected == HomeTab.PROFILE,
            onClick = { onSelect(HomeTab.PROFILE) },
            icon = { Icon(Icons.Filled.Person, contentDescription = "Profile") },
            label = { Text("Profile") },
            alwaysShowLabel = false
        )
        NavigationBarItem(
            selected = selected == HomeTab.SETTINGS,
            onClick = { onSelect(HomeTab.SETTINGS) },
            icon = { Icon(Icons.Filled.Settings, contentDescription = "Settings") },
            label = { Text("Settings") },
            alwaysShowLabel = false
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun HomePreview() {
    MaterialTheme { HomeScreen() }
}