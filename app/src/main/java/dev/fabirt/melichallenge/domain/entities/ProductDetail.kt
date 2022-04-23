package dev.fabirt.melichallenge.domain.entities

data class ProductDetail(
    val id: String,
    val siteId: String,
    val title: String,
    val subtitle: String?,
    val price: Long,
    val basePrice: Long,
    val soldQuantity: Long,
    val availableQuantity: Long,
    val thumbnail: String,
    val pictures: List<Picture>,
    val shipping: ProductShipping
)
