package com.losmoviles.features.catalog.ui.view_models

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.losmoviles.features.catalog.domain.entities.Character
import com.losmoviles.features.catalog.domain.repositories.CharactersRepository
import com.losmoviles.features.catalog.use_cases.GetCharactersPage
import kotlinx.coroutines.launch

data class CharactersUiState(
    val isLoadingInitial: Boolean = false,
    val isLoadingMore: Boolean = false,
    val items: List<Character> = emptyList(),
    val nextPage: Int? = 1,
    val error: String? = null
)

class CharactersViewModel(
    repo: CharactersRepository
) : ViewModel() {

    private val getCharactersPage = GetCharactersPage(repo)

    var state by mutableStateOf(CharactersUiState())
        private set

    fun loadFirstPage() {
        if (state.isLoadingInitial || state.items.isNotEmpty()) return
        state = state.copy(isLoadingInitial = true, error = null)
        viewModelScope.launch {
            val result = getCharactersPage(page = 1)
            state = result.fold(
                onSuccess = { page ->
                    state.copy(
                        isLoadingInitial = false,
                        items = page.items,
                        nextPage = page.nextPage,
                        error = null
                    )
                },
                onFailure = { e ->
                    state.copy(isLoadingInitial = false, error = e.message ?: "Unknown error")
                }
            )
        }
    }

    fun loadNextPage() {
        val next = state.nextPage ?: return
        if (state.isLoadingMore) return
        state = state.copy(isLoadingMore = true)
        viewModelScope.launch {
            val result = getCharactersPage(page = next)
            state = result.fold(
                onSuccess = { page ->
                    state.copy(
                        isLoadingMore = false,
                        items = state.items + page.items,
                        nextPage = page.nextPage,
                        error = null
                    )
                },
                onFailure = { e ->
                    state.copy(isLoadingMore = false, error = e.message ?: "Unknown error")
                }
            )
        }
    }
}
