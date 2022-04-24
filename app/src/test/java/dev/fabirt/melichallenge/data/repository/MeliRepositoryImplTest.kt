package dev.fabirt.melichallenge.data.repository

import arrow.core.Either
import dev.fabirt.melichallenge.TestUtil
import dev.fabirt.melichallenge.data.mapper.toDomainEntity
import dev.fabirt.melichallenge.data.network.entities.ProductDetailDto
import dev.fabirt.melichallenge.data.network.entities.ProductSearchResultDto
import dev.fabirt.melichallenge.data.network.service.MeliService
import dev.fabirt.melichallenge.error.AppException
import kotlinx.coroutines.runBlocking
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test

class MeliRepositoryImplTest {

    private lateinit var repositoryImpl: MeliRepositoryImpl

    @Before
    fun setup() {
        val mockMeliService = object : MeliService {
            override suspend fun searchProduct(
                query: String,
                limit: Int,
                offset: Int
            ): ProductSearchResultDto {
                throw AppException.Network
            }

            override suspend fun searchDetail(id: String): ProductDetailDto {
                return TestUtil.detailDto
            }

        }
        repositoryImpl = MeliRepositoryImpl(mockMeliService)
    }

    @Test
    fun repository_shouldHandleException() {
        runBlocking {
            val result = repositoryImpl.searchProduct("", 1, 0)
            assertTrue(result.isLeft())
        }
    }

    @Test
    fun repository_shouldMapDTO() {
        runBlocking {
            val result = repositoryImpl.searchDetail(TestUtil.expectedDetailId)
            assertTrue(result.isRight())
            assertEquals(TestUtil.detailDto.toDomainEntity(), result.orNull())
        }
    }
}