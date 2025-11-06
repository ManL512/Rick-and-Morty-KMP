package com.losmoviles.features.catalog.ui.view_models

import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.losmoviles.features.catalog.domain.entities.Character
import com.losmoviles.features.catalog.domain.repositories.CharactersRepository
import com.losmoviles.features.catalog.use_cases.GetCharactersPage
import kotlinx.coroutines.launch

data class CharactersUiState(
    val isLoading: Boolean = false,
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
        if (state.isLoading) return
        state = state.copy(isLoading = true, error = null)
        viewModelScope.launch {
            val result = getCharactersPage(page = 1)
            state = result.fold(
                onSuccess = { page ->
                    CharactersUiState(
                        isLoading = false,
                        items = page.items,
                        nextPage = page.nextPage
                    )
                },
                onFailure = { e ->
                    state.copy(isLoading = false, error = e.message ?: "Unknown error")
                }
            )
        }
    }

    fun loadNextPage() {
        val next = state.nextPage ?: return
        if (state.isLoading) return
        state = state.copy(isLoading = true)
        viewModelScope.launch {
            val result = getCharactersPage(page = next)
            state = result.fold(
                onSuccess = { page ->
                    state.copy(
                        isLoading = false,
                        items = state.items + page.items,
                        nextPage = page.nextPage
                    )
                },
                onFailure = { e ->
                    state.copy(isLoading = false, error = e.message ?: "Unknown error")
                }
            )
        }
    }
}
