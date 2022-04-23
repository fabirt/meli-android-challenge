package dev.fabirt.melichallenge.data.network.service

import dev.fabirt.melichallenge.data.network.client.MeliApiClient
import dev.fabirt.melichallenge.data.network.constant.ITEM_PATH
import dev.fabirt.melichallenge.data.network.constant.SEARCH_PATH
import dev.fabirt.melichallenge.data.network.entities.ProductDetailDto
import dev.fabirt.melichallenge.data.network.entities.ProductSearchResultDto
import io.ktor.client.request.parameter
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.decodeFromJsonElement

class MeliServiceImpl(
    private val client: MeliApiClient
) : MeliService {

    private val json = Json {
        ignoreUnknownKeys = true
    }

    override suspend fun searchProduct(
        query: String,
        limit: Int,
        offset: Int
    ): ProductSearchResultDto {
        val response = client.get(SEARCH_PATH) {
            parameter("q", query)
            parameter("limit", limit)
            parameter("offset", offset)
        }
        return json.decodeFromJsonElement(response)
    }

    override suspend fun searchDetail(id: String): ProductDetailDto {
        val response = client.get(ITEM_PATH.replace(":id", id))
        return json.decodeFromJsonElement(response)
    }
}