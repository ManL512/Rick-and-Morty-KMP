package com.losmoviles.shared.ui

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.losmoviles.features.home.ui.composables.HomeBottomBar
import com.losmoviles.features.home.ui.composables.HomeTab
import com.losmoviles.shared.navigation.AppNavGraph
import com.losmoviles.shared.navigation.Destinations

@Composable
fun MainScaffold(
    navController: NavHostController,
    isDarkTheme: Boolean,
    onToggleTheme: () -> Unit,
) {
    val backStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = backStackEntry?.destination?.route

    val selectedTab = when (currentRoute) {
        Destinations.HOME -> HomeTab.HOME
        Destinations.FAVORITES -> HomeTab.FAVORITES
        Destinations.CATALOG -> HomeTab.CATALOG
        Destinations.PROFILE -> HomeTab.PROFILE
        Destinations.SETTINGS -> HomeTab.SETTINGS
        else -> HomeTab.HOME
    }

    Scaffold(
        bottomBar = {
            HomeBottomBar(
                selected = selectedTab,
                onSelect = { tab ->
                    val route = when (tab) {
                        HomeTab.HOME -> Destinations.HOME
                        HomeTab.FAVORITES -> Destinations.FAVORITES
                        HomeTab.CATALOG -> Destinations.CATALOG
                        HomeTab.PROFILE -> Destinations.PROFILE
                        HomeTab.SETTINGS -> Destinations.SETTINGS
                    }
                    if (route != currentRoute) {
                        navController.navigate(route) {
                            launchSingleTop = true
                            restoreState = true
                            popUpTo(Destinations.HOME) {
                                saveState = true
                            }
                        }

                    }
                }
            )
        }
    ) { inner ->
        Box(Modifier.padding(inner)) {
            AppNavGraph(
                navController = navController,
                isDarkTheme = isDarkTheme,
                onToggleTheme = onToggleTheme
            )
        }
    }
}