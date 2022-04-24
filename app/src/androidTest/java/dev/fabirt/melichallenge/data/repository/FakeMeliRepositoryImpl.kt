package dev.fabirt.melichallenge.data.repository

import arrow.core.Either
import dev.fabirt.melichallenge.domain.entities.*
import dev.fabirt.melichallenge.domain.repository.MeliRepository
import dev.fabirt.melichallenge.error.Failure

class FakeMeliRepositoryImpl : MeliRepository {
    override suspend fun searchProduct(
        query: String,
        limit: Int,
        offset: Int
    ): Either<Failure, ProductSearchResult> {
        return Either.Right(
            ProductSearchResult(
                siteId = "MCO",
                query = query,
                paging = SearchPaging(
                    total = 1,
                    primaryResults = 0,
                    offset = offset,
                    limit = limit
                ),
                results = listOf(
                    Product(
                        id = "MCO857660267",
                        title = "Smart Tv Samsung Series 8 Un50au8000kxzl Led 4k 50  100v/240v",
                        price = 2129900,
                        thumbnail = "http://http2.mlstatic.com/D_866962-MLA47916973959_102021-I.jpg",
                        shipping = ProductShipping(
                            freeShipping = true,
                            storePickUp = false
                        )
                    )
                )
            )
        )
    }

    override suspend fun searchDetail(id: String): Either<Failure, ProductDetail> {
        return Either.Right(
            ProductDetail(
                id = id,
                siteId = "MCO",
                title = "Smart Tv Samsung Series 8 Un50au8000kxzl Led 4k 50  100v/240v",
                subtitle = null,
                price = 2129900,
                basePrice = 2159900,
                soldQuantity = 100,
                availableQuantity = 23,
                thumbnail = "http://http2.mlstatic.com/D_866962-MLA47916973959_102021-I.jpg",
                permalink = "https://www.mercadolibre.com.co/smart-tv-samsung-series-8-un50au8000kxzl-led-4k-50-100v240v/p/MCO18437474",
                shipping = ProductShipping(
                    freeShipping = true,
                    storePickUp = false
                ),
                pictures = listOf(
                    Picture(
                        id = "1",
                        url = "http://http2.mlstatic.com/D_866962-MLA47916973959_102021-O.jpg"
                    ),
                    Picture(
                        id = "2",
                        url = "http://http2.mlstatic.com/D_973614-MLA47917179380_102021-O.jpg"
                    )
                )
            )
        )
    }
}