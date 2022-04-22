package dev.fabirt.melichallenge.data.network.interceptor

import io.ktor.client.statement.HttpResponse

interface BaseResponseInterceptor<out T> {

    suspend operator fun invoke(response: HttpResponse): T
}