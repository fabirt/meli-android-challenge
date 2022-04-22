package dev.fabirt.melichallenge.data.network.service

import dev.fabirt.melichallenge.data.network.client.MeliApiClient
import dev.fabirt.melichallenge.data.network.constant.PRODUCT_SEARCH_PATH
import dev.fabirt.melichallenge.data.network.entities.ProductSearchResultDto
import io.ktor.client.request.parameter
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.decodeFromJsonElement

class MeliServiceImpl(
    private val client: MeliApiClient
) : MeliService {

    override suspend fun searchProduct(
        query: String,
        limit: Int,
        offset: Int
    ): ProductSearchResultDto {
        val json = client.get(PRODUCT_SEARCH_PATH) {
            parameter("query", query)
            parameter("limit", limit)
            parameter("offset", offset)
        }

        return Json.decodeFromJsonElement(json)
    }
}