package dev.fabirt.melichallenge.data.mapper

import dev.fabirt.melichallenge.data.network.entities.ProductDto
import dev.fabirt.melichallenge.data.network.entities.ProductSearchResultDto
import dev.fabirt.melichallenge.data.network.entities.ProductShippingDto
import dev.fabirt.melichallenge.data.network.entities.SearchPagingDto
import dev.fabirt.melichallenge.domain.entities.Product
import dev.fabirt.melichallenge.domain.entities.ProductSearchResult
import dev.fabirt.melichallenge.domain.entities.ProductShipping
import dev.fabirt.melichallenge.domain.entities.SearchPaging

fun SearchPagingDto.toDomainEntity() = SearchPaging(
    total, primaryResults, offset, limit
)

fun ProductSearchResultDto.toDomainEntity() = ProductSearchResult(
    siteId, query, paging.toDomainEntity(), results.toDomainList()
)

fun ProductShippingDto.toDomainEntity() = ProductShipping(freeShipping, storePickUp)

fun ProductDto.toDomainEntity() = Product(
    id, title, price, thumbnail, shipping.toDomainEntity()
)

fun List<ProductDto>.toDomainList() = map { it.toDomainEntity() }