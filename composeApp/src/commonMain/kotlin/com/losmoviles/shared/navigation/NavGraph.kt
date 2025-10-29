package com.losmoviles.shared.navigation


import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.losmoviles.features.home.ui.screens.HomeScreen
import com.losmoviles.features.favorites.ui.screens.FavoritesScreen

@Composable
fun AppNavGraph(navController: NavHostController) {
    NavHost(
        navController = navController,
        startDestination = Destinations.HOME
    ) {
        // 🏠 Home
        composable(Destinations.HOME) {
            HomeScreen(navController)
        }

        composable(Destinations.FAVORITES) {
            FavoritesScreen(
                onBack = { navController.popBackStack() }
            )
        }
    }
}
