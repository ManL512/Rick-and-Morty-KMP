package com.losmoviles.features.catalog.infrastructure

import com.losmoviles.features.catalog.domain.entities.Character
import com.losmoviles.features.catalog.domain.repositories.CharactersPage
import com.losmoviles.features.catalog.domain.repositories.CharactersRepository
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.request.get
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import kotlinx.serialization.json.Json

class CharactersRepositoryImpl(
    private val client: HttpClient = defaultClient()
) : CharactersRepository {

    override suspend fun getCharacters(page: Int): Result<CharactersPage> = runCatching {
        val dto: CharactersResponseDto = client
            .get("https://rickandmortyapi.com/api/character?page=$page")
            .body()

        val items = dto.results.map { it.toDomain() }
        val next = dto.info.nextPageNumberOrNull()

        CharactersPage(items = items, nextPage = next)
    }
}

// -------- DTOs --------

@Serializable
data class CharactersResponseDto(
    val info: InfoDto,
    val results: List<CharacterDto>
)

@Serializable
data class InfoDto(
    val next: String? = null
)

@Serializable
data class CharacterDto(
    val id: Int,
    val name: String,
    val status: String,
    val species: String,
    val image: String,
    val location: LocationDto
)

@Serializable
data class LocationDto(
    val name: String
)

// -------- Mapping --------
private fun CharacterDto.toDomain() = Character(
    id = id,
    name = name,
    image = image,
    status = status,
    species = species,
    locationName = location.name
)

private fun InfoDto.nextPageNumberOrNull(): Int? {
    // next = "https://rickandmortyapi.com/api/character?page=3"
    val url = next ?: return null
    val idx = url.indexOf("page=")
    return if (idx >= 0) url.substring(idx + 5).toIntOrNull() else null
}

@OptIn(ExperimentalSerializationApi::class)
private fun defaultClient(): HttpClient =
    HttpClient {
        install(ContentNegotiation) {
            json(Json {
                ignoreUnknownKeys = true
                explicitNulls = false
            })
        }
    }
