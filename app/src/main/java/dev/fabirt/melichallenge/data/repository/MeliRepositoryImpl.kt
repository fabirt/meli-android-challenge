package dev.fabirt.melichallenge.data.repository

import arrow.core.Either
import dev.fabirt.melichallenge.data.mapper.toDomainEntity
import dev.fabirt.melichallenge.data.network.service.MeliService
import dev.fabirt.melichallenge.domain.entities.ProductSearchResult
import dev.fabirt.melichallenge.domain.repository.MeliRepository
import dev.fabirt.melichallenge.error.Failure
import dev.fabirt.melichallenge.util.runCatching as myRunCatching

class MeliRepositoryImpl(
    private val service: MeliService
) : MeliRepository {
    override suspend fun searchProduct(
        query: String,
        limit: Int,
        offset: Int
    ): Either<Failure, ProductSearchResult> {
        return myRunCatching {
            val result = service.searchProduct(query, limit, offset)
            Either.Right(result.toDomainEntity())
        }
    }
}