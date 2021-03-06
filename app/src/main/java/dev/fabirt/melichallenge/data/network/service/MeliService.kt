package dev.fabirt.melichallenge.data.network.service

import dev.fabirt.melichallenge.data.network.entities.ProductDetailDto
import dev.fabirt.melichallenge.data.network.entities.ProductSearchResultDto

interface MeliService {

    suspend fun searchProduct(
        query: String,
        limit: Int,
        offset: Int,
    ): ProductSearchResultDto

    suspend fun searchDetail(id: String): ProductDetailDto
}