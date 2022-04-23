package dev.fabirt.melichallenge.data.mapper

import dev.fabirt.melichallenge.data.network.entities.*
import dev.fabirt.melichallenge.domain.entities.*

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

fun PictureDto.toDomainEntity() = Picture(id, url)

fun ProductDetailDto.toDomainEntity() = ProductDetail(
    id = id,
    siteId = siteId,
    title = title,
    subtitle = subtitle,
    price = price,
    basePrice = basePrice,
    soldQuantity = soldQuantity,
    availableQuantity = availableQuantity,
    thumbnail = thumbnail,
    permalink = permalink,
    pictures = pictures.map { it.toDomainEntity() },
    shipping = shipping.toDomainEntity()
)