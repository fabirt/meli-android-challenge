package dev.fabirt.melichallenge.data.network.entities

import kotlinx.serialization.Serializable

@Serializable
data class ProductShippingDto(
    val freeShipping: Boolean,
    val storePickUp: Boolean
)
