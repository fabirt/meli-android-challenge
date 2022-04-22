package dev.fabirt.melichallenge.data.network.client

import dev.fabirt.melichallenge.data.network.interceptor.BaseResponseInterceptor
import io.ktor.client.*
import io.ktor.client.request.*
import io.ktor.client.statement.*

abstract class BaseHttpClient<T>(
    private val httpClient: HttpClient,
    private val onResponse: BaseResponseInterceptor<T>
) {

    protected abstract val baseUrl: String

    suspend fun get(path: String, block: HttpRequestBuilder.() -> Unit = {}): T {
        val response = httpClient.get<HttpResponse>(concatBaseUrl(path), block)
        return onResponse(response)
    }

    suspend fun post(path: String, block: HttpRequestBuilder.() -> Unit = {}): T {
        val response = httpClient.post<HttpResponse>(concatBaseUrl(path), block)
        return onResponse(response)
    }

    private fun concatBaseUrl(path: String): String = "$baseUrl$path"
}