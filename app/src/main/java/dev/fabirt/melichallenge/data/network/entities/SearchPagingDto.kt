package dev.fabirt.melichallenge.data.network.entities

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class SearchPagingDto(
    val total: Int,
    @SerialName("primary_results")
    val primaryResults: Int,
    val offset: Int,
    val limit: Int
)
