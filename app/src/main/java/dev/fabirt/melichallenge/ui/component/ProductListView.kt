package dev.fabirt.melichallenge.ui.component

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.runtime.Composable
import dev.fabirt.melichallenge.domain.entities.Product

@Composable
fun ProductListView(
    data: List<Product>,
    onItemClick: (Int, Product) -> Unit
) {
    LazyColumn {
        itemsIndexed(data) { index: Int, item: Product ->
            ProductView(product = item) {
                onItemClick(index, item)
            }
        }
    }
}