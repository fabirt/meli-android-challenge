package dev.fabirt.melichallenge.data.network.entities

import kotlinx.serialization.Serializable

@Serializable
data class ProductSearchResultDto(
    val siteId: String,
    val query: String,
    val paging: SearchPagingDto,
    val results: List<ProductDto>
)
