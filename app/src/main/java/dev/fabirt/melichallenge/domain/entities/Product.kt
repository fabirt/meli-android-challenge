package dev.fabirt.melichallenge.domain.entities

data class Product(
    val id: String,
    val title: String,
    val price: Long,
    val thumbnail: String,
    val shipping: ProductShipping
)
