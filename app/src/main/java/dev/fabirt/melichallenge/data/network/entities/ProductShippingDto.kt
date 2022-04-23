package dev.fabirt.melichallenge.data.network.entities

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ProductShippingDto(
    @SerialName("free_shipping")
    val freeShipping: Boolean,
    @SerialName("store_pick_up")
    val storePickUp: Boolean
)
