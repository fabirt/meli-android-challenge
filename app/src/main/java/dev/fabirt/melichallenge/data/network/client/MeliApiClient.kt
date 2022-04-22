package dev.fabirt.melichallenge.data.network.client

import dev.fabirt.melichallenge.data.network.interceptor.MeliApiResponseInterceptor
import io.ktor.client.*
import kotlinx.serialization.json.JsonObject

class MeliApiClient(
    httpClient: HttpClient
) : BaseHttpClient<JsonObject>(
    httpClient,
    MeliApiResponseInterceptor()
) {
    override val baseUrl: String
        get() = "https://api.mercadolibre.com"
}