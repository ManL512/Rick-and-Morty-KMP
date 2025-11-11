package com.losmoviles.features.catalog.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.GridItemSpan
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.grid.rememberLazyGridState
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.losmoviles.features.catalog.infrastructure.CharactersRepositoryImpl
import com.losmoviles.features.catalog.ui.composables.CharacterCard
import com.losmoviles.features.catalog.ui.view_models.CharactersViewModel
import com.losmoviles.shared.ui.screens.CustomScreen
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.filter
import kotlinx.coroutines.flow.map

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CharactersScreen(onBack: (() -> Unit)? = null) {
    val vm = remember { CharactersViewModel(CharactersRepositoryImpl()) }
    val state = vm.state

    LaunchedEffect(Unit) { vm.loadFirstPage() }

    val gridState = rememberLazyGridState()

    // Auto-paginación para Grid
    LaunchedEffect(gridState, state.items.size, state.nextPage, state.isLoadingMore) {
        snapshotFlow {
            val last = gridState.layoutInfo.visibleItemsInfo.lastOrNull()?.index ?: 0
            val total = gridState.layoutInfo.totalItemsCount
            last to total
        }
            .map { (last, total) -> total > 0 && last >= total - 6 }
            .distinctUntilChanged()
            .filter { it && state.nextPage != null && !state.isLoadingMore }
            .collect { vm.loadNextPage() }
    }

    CustomScreen(
        title = "Catalog",
        onBack = onBack
    ) {
        when {
            state.isLoadingInitial && state.items.isEmpty() -> {
                Box(Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                    CircularProgressIndicator()
                }
            }
            state.error != null && state.items.isEmpty() -> {
                Box(Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                    Text("Error: ${state.error}")
                }
            }
            else -> {
                Box(Modifier.fillMaxSize()) {
                    LazyVerticalGrid(
                        state = gridState,
                        columns = GridCells.Fixed(2),
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(horizontal = 12.dp),
                        contentPadding = PaddingValues(12.dp),
                        horizontalArrangement = Arrangement.spacedBy(12.dp),
                        verticalArrangement = Arrangement.spacedBy(12.dp)
                    ) {
                        items(state.items, key = { it.id }) { character ->
                            CharacterCard(character = character)
                        }


                        if (state.isLoadingMore) {
                            item(span = { GridItemSpan(2) }) {
                                Box(
                                    Modifier
                                        .fillMaxWidth()
                                        .padding(16.dp),
                                    contentAlignment = Alignment.Center
                                ) { CircularProgressIndicator() }
                            }
                        }
                    }

                    if (state.error != null && state.items.isNotEmpty()) {
                        SnackbarHost(
                            hostState = remember { SnackbarHostState() },
                            modifier = Modifier
                                .align(Alignment.BottomCenter)
                                .padding(12.dp)
                        ) { Snackbar { Text("Error: ${state.error}") } }
                    }
                }
            }
        }
    }
}

