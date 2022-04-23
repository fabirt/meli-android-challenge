package dev.fabirt.melichallenge.domain.entities

import kotlinx.serialization.json.JsonObject

data class ProductSearchResult(
    val siteId: String,
    val query: String,
    val paging: SearchPaging,
    val results: List<JsonObject>
)
