package com.losmoviles.features.catalog.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.losmoviles.features.catalog.infrastructure.CharactersRepositoryImpl
import com.losmoviles.features.catalog.ui.view_models.CharactersViewModel
import com.losmoviles.shared.ui.screens.CustomScreen

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CharactersScreen(
    onBack: (() -> Unit)? = null
) {
    val vm = remember { CharactersViewModel(CharactersRepositoryImpl()) }
    val state = vm.state
    LaunchedEffect(Unit) { vm.loadFirstPage()
    }

    CustomScreen(
        title = "Catalog",
        onBack = onBack,
    ) {
        when {
            state.isLoading && state.items.isEmpty() -> {
                Box(Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                    CircularProgressIndicator()
                }
            }
            state.error != null -> {
                Box(Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                    Text("Error: ${state.error}")
                }
            }
            else -> {
                LazyColumn(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(16.dp),
                    verticalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    items(state.items) { c ->
                        ElevatedCard {
                            Column(Modifier.padding(12.dp)) {
                                Text(c.name, style = MaterialTheme.typography.titleMedium)
                                Spacer(Modifier.height(4.dp))
                                Text("${c.species} • ${c.status}")
                                Spacer(Modifier.height(2.dp))
                                Text("Location: ${c.locationName}", color = MaterialTheme.colorScheme.onSurfaceVariant)
                            }
                        }
                    }

                    // Botón básico para cargar más (paginación manual)
                    item {
                        if (state.nextPage != null) {
                            Spacer(Modifier.height(12.dp))
                            Button(
                                onClick = { vm.loadNextPage() },
                                modifier = Modifier.fillMaxWidth()
                            ) {
                                Text("Load more")
                            }
                        }
                    }
                }
            }
        }
    }
}
