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
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.*
import androidx.compose.runtime.remember
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.losmoviles.features.home.ui.composables.*
import com.losmoviles.shared.navigation.Destinations
import com.losmoviles.shared.ui.screens.CustomScreen

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(navController: NavHostController) {
    CustomScreen(
        title = "",
        onBack = null,
        bottomBar = {
            HomeBottomBar(
                selected = HomeTab.HOME,
                onSelect = { tab ->
                    when (tab) {
                        HomeTab.HOME -> Unit
                        HomeTab.FAVORITES -> navController.navigate(Destinations.FAVORITES)
                        else -> Unit
                    }
                }
            )
        }
    ) {
        Column(
            modifier = Modifier
                .padding(16.dp)
                .fillMaxSize(),
            verticalArrangement = Arrangement.spacedBy(8.dp),
        ) {
            WelcomeRow(title = "Welcome back,", name = "Miguel Navas")
//            Spacer(Modifier.height(16.dp))
            SearchRow()
//            Spacer(Modifier.height(24.dp))d
            CategoryRow()
//            Spacer(Modifier.height(20.dp))
            SectionHeader(title = "For You", action = "See all")
//            Spacer(Modifier.height(8.dp))
            val mock = remember { mockCharacters() }
            CharacterGrid(mock)
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun HomePreview() {
    val navController = androidx.navigation.compose.rememberNavController()
    MaterialTheme { HomeScreen(navController) }
}