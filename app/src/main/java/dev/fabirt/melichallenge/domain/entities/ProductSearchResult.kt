package dev.fabirt.melichallenge.domain.entities

data class ProductSearchResult(
    val siteId: String,
    val query: String,
    val paging: SearchPaging,
    val results: List<Product>
)
