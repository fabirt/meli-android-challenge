package dev.fabirt.melichallenge.data.network.entities

import kotlinx.serialization.Serializable

@Serializable
data class ProductDto(
    val id: String,
    val title: String,
    val price: Long,
    val thumbnail: String,
    val shipping: ProductShippingDto
)
