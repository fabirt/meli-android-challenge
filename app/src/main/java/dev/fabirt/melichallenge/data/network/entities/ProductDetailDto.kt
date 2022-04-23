package dev.fabirt.melichallenge.data.network.entities

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ProductDetailDto(
    val id: String,
    @SerialName("site_id")
    val siteId: String,
    val title: String,
    val subtitle: String?,
    val price: Long,
    @SerialName("base_price")
    val basePrice: Long,
    @SerialName("sold_quantity")
    val soldQuantity: Long,
    @SerialName("available_quantity")
    val availableQuantity: Long,
    @SerialName("secure_thumbnail")
    val thumbnail: String,
    val permalink: String,
    val pictures: List<PictureDto>,
    val shipping: ProductShippingDto
)
