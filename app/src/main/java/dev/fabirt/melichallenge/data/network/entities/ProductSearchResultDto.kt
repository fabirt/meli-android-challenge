package dev.fabirt.melichallenge.data.network.entities

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ProductSearchResultDto(
    @SerialName("site_id")
    val siteId: String,
    val query: String,
    val paging: SearchPagingDto,
    val results: List<ProductDto>
)
