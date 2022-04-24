package dev.fabirt.melichallenge

import dev.fabirt.melichallenge.data.network.entities.PictureDto
import dev.fabirt.melichallenge.data.network.entities.ProductDetailDto
import dev.fabirt.melichallenge.data.network.entities.ProductShippingDto

object TestUtil {

    val encodedSearchResult = """
        {"site_id":"MCO","query":"tv","paging":{"total":2368,"primary_results":1000,"offset":0,"limit":1},"results":[{"id":"MCO857660267","site_id":"MCO","title":"Smart Tv Samsung Series 8 Un50au8000kxzl Led 4k 50  100v/240v","price":2129900,
        "sale_price":null,"currency_id":"COP","available_quantity":3,"sold_quantity":60,"buying_mode":"buy_it_now","listing_type_id":"gold_special","stop_time":"2042-02-10T17:45:43.000Z","condition":"new","permalink":"https://www.mercadolibre.com.co/smart-tv-samsung-series-8-un50au8000kxzl-led-4k-50-100v240v/p/MCO18437474","thumbnail":"http://http2.mlstatic.com/D_866962-MLA47916973959_102021-I.jpg","thumbnail_id":"866962-MLA47916973959_102021","accepts_mercadopago":true,"shipping":{"free_shipping":true,"mode":"me2","tags":["self_service_out","mandatory_free_shipping"],"logistic_type":"cross_docking","store_pick_up":false}}]}
    """.trimIndent()

    val encodedItemDetail = """
        {"id": "MCO857660267","site_id": "MCO","title": "Smart Tv Samsung Series 8 Un50au8000kxzl Led 4k 50 100v/240v","subtitle": null,
        "price": 2129900,"base_price": 2129900,"original_price": 2129900,"available_quantity": 1,"sold_quantity": 50,
        "permalink": "https://articulo.mercadolibre.com.co/MCO-857660267-smart-tv-samsung-series-8-un50au8000kxzl-led-4k-50-100v240v-_JM",
        "thumbnail": "http://http2.mlstatic.com/D_866962-MLA47916973959_102021-I.jpg","secure_thumbnail": "https://http2.mlstatic.com/D_866962-MLA47916973959_102021-I.jpg",
        "pictures": [
            {
              "id": "866962-MLA47916973959_102021",
              "url": "http://http2.mlstatic.com/D_866962-MLA47916973959_102021-O.jpg",
              "secure_url": "https://http2.mlstatic.com/D_866962-MLA47916973959_102021-O.jpg",
              "size": "500x311",
              "max_size": "1048x652",
              "quality": ""
            },
            {
              "id": "973614-MLA47917179380_102021",
              "url": "http://http2.mlstatic.com/D_973614-MLA47917179380_102021-O.jpg",
              "secure_url": "https://http2.mlstatic.com/D_973614-MLA47917179380_102021-O.jpg",
              "size": "500x454",
              "max_size": "1200x1091",
              "quality": ""
            },
            {
              "id": "633576-MLA47917179381_102021",
              "url": "http://http2.mlstatic.com/D_633576-MLA47917179381_102021-O.jpg",
              "secure_url": "https://http2.mlstatic.com/D_633576-MLA47917179381_102021-O.jpg",
              "size": "241x500",
              "max_size": "509x1056",
              "quality": ""
            }
        ],
        "shipping":{"free_shipping": true,"store_pick_up": false}
        }
    """.trimIndent()

    const val expectedPagingTotal = 2368

    const val expectedDetailId = "MCO857660267"

    val detailDto = ProductDetailDto(
        id = "MCO857660267",
        siteId = "MCO",
        title = "Smart Tv Samsung Series 8 Un50au8000kxzl Led 4k 50 100v/240v",
        subtitle = null,
        price = 2129900,
        basePrice = 2129900,
        soldQuantity = 50,
        availableQuantity = 1,
        thumbnail = "http://http2.mlstatic.com/D_866962-MLA47916973959_102021-I.jpg\",\"secure_thumbnail\": \"https://http2.mlstatic.com/D_866962-MLA47916973959_102021-I.jpg",
        permalink = "https://articulo.mercadolibre.com.co/MCO-857660267-smart-tv-samsung-series-8-un50au8000kxzl-led-4k-50-100v240v-_JM",
        pictures = listOf(
            PictureDto("", ""),
            PictureDto("", "")
        ),
        shipping = ProductShippingDto(
            freeShipping = true,
            storePickUp = false
        )
    )
}