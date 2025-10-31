package com.losmoviles.features.home.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.losmoviles.features.home.ui.composables.*
import com.losmoviles.shared.ui.screens.CustomScreen
import org.jetbrains.compose.ui.tooling.preview.Preview

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen() {
    CustomScreen(
        title = "",
        onBack = null,
        actions = {
            IconButton(onClick = { /* TODO menu */ }) {
                Icon(Icons.Filled.Menu, contentDescription = "Menu")
            }
        }
        // ⬇️ bottomBar eliminado; ahora vive en MainScaffold
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 16.dp)
        ) {
            Spacer(Modifier.height(8.dp))
            WelcomeRow(title = "Welcome back,", name = "Miguel Navas")
            Spacer(Modifier.height(16.dp))
            SearchRow()
            Spacer(Modifier.height(24.dp))
            CategoryRow()
            Spacer(Modifier.height(20.dp))
            SectionHeader(title = "For You", action = "See all")
            Spacer(Modifier.height(8.dp))

            val mock = remember { mockCharacters() }
            CharacterGrid(mock)

            Spacer(Modifier.height(16.dp))
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun HomePreview() {
    MaterialTheme { HomeScreen() }
}
