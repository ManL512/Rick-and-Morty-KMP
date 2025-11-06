package com.losmoviles.features.catalog.use_cases

import com.losmoviles.features.catalog.domain.repositories.CharactersPage
import com.losmoviles.features.catalog.domain.repositories.CharactersRepository

class GetCharactersPage(
    private val repo: CharactersRepository
) {
    suspend operator fun invoke(page: Int): Result<CharactersPage> =
        repo.getCharacters(page)
}
