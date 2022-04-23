package dev.fabirt.melichallenge.domain.repository

import arrow.core.Either
import dev.fabirt.melichallenge.domain.entities.ProductSearchResult
import dev.fabirt.melichallenge.error.Failure

interface MeliRepository {
    suspend fun searchProduct(
        query: String,
        limit: Int,
        offset: Int,
    ): Either<Failure, ProductSearchResult>
}