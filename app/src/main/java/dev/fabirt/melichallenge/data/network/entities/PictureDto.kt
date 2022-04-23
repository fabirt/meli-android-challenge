package dev.fabirt.melichallenge.data.network.entities

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class PictureDto(
    val id: String,
    @SerialName("secure_url")
    val url: String
)
