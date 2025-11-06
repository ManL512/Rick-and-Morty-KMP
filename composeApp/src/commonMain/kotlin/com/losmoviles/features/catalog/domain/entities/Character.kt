package com.losmoviles.features.catalog.domain.entities

data class Character(
    val id: Int,
    val name: String,
    val image: String,
    val status: String,
    val species: String,
    val locationName: String
)
