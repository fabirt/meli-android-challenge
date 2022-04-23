package dev.fabirt.melichallenge.data.mapper

import dev.fabirt.melichallenge.data.network.entities.ProductSearchResultDto
import dev.fabirt.melichallenge.data.network.entities.SearchPagingDto
import dev.fabirt.melichallenge.domain.entities.ProductSearchResult
import dev.fabirt.melichallenge.domain.entities.SearchPaging

fun SearchPagingDto.toDomainEntity() = SearchPaging(
    total, primaryResults, offset, limit
)

fun ProductSearchResultDto.toDomainEntity() = ProductSearchResult(
    siteId, query, paging.toDomainEntity(), results
)