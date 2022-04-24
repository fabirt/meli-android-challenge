package dev.fabirt.melichallenge.data.network.service

import dev.fabirt.melichallenge.TestUtil
import dev.fabirt.melichallenge.data.network.client.KtorHttpClient
import dev.fabirt.melichallenge.data.network.client.MeliApiClient
import dev.fabirt.melichallenge.data.network.constant.SEARCH_PATH
import io.ktor.client.engine.mock.*
import io.ktor.http.*
import io.ktor.utils.io.*
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test

class MeliServiceImplTest {

    private lateinit var serviceImpl: MeliServiceImpl

    @Before
    fun setup() {
        val engine = MockEngine { request ->
            val requestPath = request.url.encodedPath
            if (requestPath == SEARCH_PATH) {
                return@MockEngine respond(
                    content = ByteReadChannel(TestUtil.encodedSearchResult),
                    status = HttpStatusCode.OK,
                    headers = headersOf(HttpHeaders.ContentType, "application/json")
                )
            }
            respond(
                content = ByteReadChannel(TestUtil.encodedItemDetail),
                status = HttpStatusCode.OK,
                headers = headersOf(HttpHeaders.ContentType, "application/json")
            )
        }
        val httpClient = KtorHttpClient.create(engine)
        val apiClient = MeliApiClient(httpClient)
        serviceImpl = MeliServiceImpl(apiClient)
    }

    @Test
    fun searchProduct_isCorrect() {
        runBlocking {
            val result = serviceImpl.searchProduct("tv", 1, 0)
            assertEquals(TestUtil.expectedPagingTotal, result.paging.total)
        }
    }

    @Test
    fun searchDetail_isCorrect() {
        runBlocking {
            val result = serviceImpl.searchDetail(TestUtil.expectedDetailId)
            assertEquals(TestUtil.expectedDetailId, result.id)
        }
    }
}