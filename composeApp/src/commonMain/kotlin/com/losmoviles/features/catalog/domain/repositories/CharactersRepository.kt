package com.losmoviles.features.catalog.domain.repositories

import com.losmoviles.features.catalog.domain.entities.Character

data class CharactersPage(
    val items: List<Character>,
    val nextPage: Int? // null si no hay más
)

interface CharactersRepository {
    suspend fun getCharacters(page: Int): Result<CharactersPage>
}