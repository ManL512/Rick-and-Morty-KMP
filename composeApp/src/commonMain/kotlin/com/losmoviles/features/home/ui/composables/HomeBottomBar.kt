package com.losmoviles.features.home.ui.composables

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material.icons.outlined.GridView
import androidx.compose.material.icons.outlined.PersonSearch
import androidx.compose.material3.*
import androidx.compose.runtime.Composable

enum class HomeTab { HOME, FAVORITES, CATALOG, PROFILE, SETTINGS }

@Composable
fun HomeBottomBar(selected: HomeTab, onSelect: (HomeTab) -> Unit) {
    NavigationBar {
        NavigationBarItem(selected == HomeTab.HOME, { onSelect(HomeTab.HOME) }, { Icon(Icons.Filled.Home, null) }, label = { Text("Home") }, alwaysShowLabel = false)
        NavigationBarItem(selected == HomeTab.FAVORITES, { onSelect(HomeTab.FAVORITES) }, { Icon(Icons.Filled.Favorite, null) }, label = { Text("Favorites") }, alwaysShowLabel = false)
        NavigationBarItem(
            selected == HomeTab.CATALOG,
            { onSelect(HomeTab.CATALOG) },
            { Icon(Icons.Outlined.PersonSearch, null) },
            label = { Text("Catalog") },
            alwaysShowLabel = false
        )
        NavigationBarItem(selected == HomeTab.PROFILE, { onSelect(HomeTab.PROFILE) }, { Icon(Icons.Filled.Person, null) }, label = { Text("Profile") }, alwaysShowLabel = false)
        NavigationBarItem(selected == HomeTab.SETTINGS, { onSelect(HomeTab.SETTINGS) }, { Icon(Icons.Filled.Settings, null) }, label = { Text("Settings") }, alwaysShowLabel = false)
    }
}
