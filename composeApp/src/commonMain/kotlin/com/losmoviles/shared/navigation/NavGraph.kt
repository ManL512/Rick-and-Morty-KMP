package com.losmoviles.shared.navigation


import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.losmoviles.features.favorites.ui.screens.FavoritesScreen
import com.losmoviles.features.home.ui.screens.HomeScreen
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.wrapContentSize
import com.losmoviles.features.settings.ui.screens.ConfigurationScreen
@Composable
fun AppNavGraph(
    navController: NavHostController,
    isDarkTheme: Boolean,
    onToggleTheme: () -> Unit
) {
    NavHost(
        navController = navController,
        startDestination = Destinations.HOME
    ) {
        composable(Destinations.HOME) { HomeScreen() }

        composable(Destinations.FAVORITES) {
            FavoritesScreen(onBack = { navController.popBackStack() })
        }

        composable(Destinations.CATALOG) { SimpleCenterText("Catalog") }
        composable(Destinations.PROFILE) { SimpleCenterText("Profile") }

        // Settings → ConfigurationScreen con back y toggle global
        composable(Destinations.SETTINGS) {
            ConfigurationScreen(
                onBack = { navController.popBackStack() },
                onToggleTheme = onToggleTheme,
                isDarkTheme = isDarkTheme
            )
        }
    }
}

@Composable
private fun SimpleCenterText(text: String) {
    Text(
        text = text,
        style = MaterialTheme.typography.titleLarge,
        modifier = Modifier
            .fillMaxSize()
            .wrapContentSize(align = Alignment.Center)
    )
}