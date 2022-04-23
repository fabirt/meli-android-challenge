package dev.fabirt.melichallenge.domain.entities

data class SearchPaging(
    val total: Int,
    val primaryResults: Int,
    val offset: Int,
    val limit: Int
)
