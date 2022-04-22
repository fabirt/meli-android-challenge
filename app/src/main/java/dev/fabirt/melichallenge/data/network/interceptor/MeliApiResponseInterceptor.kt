package dev.fabirt.melichallenge.data.network.interceptor

import io.ktor.client.call.*
import io.ktor.client.statement.*
import kotlinx.serialization.json.JsonObject

class MeliApiResponseInterceptor : BaseResponseInterceptor<JsonObject> {
    override suspend fun invoke(response: HttpResponse): JsonObject {
        if (response.status.value == 200) {
            return response.receive()
        }

        throw Exception(response.status.description)
    }
}