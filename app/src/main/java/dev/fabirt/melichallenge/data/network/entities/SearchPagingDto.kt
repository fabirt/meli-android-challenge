package dev.fabirt.melichallenge.data.network.entities

import kotlinx.serialization.Serializable

@Serializable
data class SearchPagingDto(
    val total: Int,
    val primaryResults: Int,
    val offset: Int,
    val limit: Int
)
