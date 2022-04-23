package dev.fabirt.melichallenge.ui.component

import androidx.compose.foundation.layout.padding
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import dev.fabirt.melichallenge.domain.entities.Product

@Composable
fun ProductView(product: Product) {

    Text(
        product.title,
        modifier = Modifier.padding(16.dp)
    )
}