package dev.fabirt.melichallenge.ui.component

import android.content.res.Configuration.UI_MODE_NIGHT_YES
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import dev.fabirt.melichallenge.R
import dev.fabirt.melichallenge.domain.entities.Product
import dev.fabirt.melichallenge.domain.entities.ProductShipping
import dev.fabirt.melichallenge.ui.theme.MeliChallengeTheme
import dev.fabirt.melichallenge.util.toCurrencyString

@Composable
fun ProductView(
    product: Product,
    onClick: () -> Unit
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .clickable(onClick = onClick)
            .padding(16.dp)
    ) {
        Box(
            modifier = Modifier
                .size(88.dp)
                .clip(RoundedCornerShape(16.dp))
                .background(Color.LightGray)
        )
        Spacer(modifier = Modifier.width(16.dp))

        Column(
            modifier = Modifier.weight(1f)
        ) {
            Text(
                product.title,
                maxLines = 2,
            )
            Spacer(modifier = Modifier.height(4.dp))
            Text(
                product.price.toCurrencyString(),
                style = MaterialTheme.typography.h6,
                maxLines = 1,
            )
            if (product.shipping.freeShipping) {
                Spacer(modifier = Modifier.height(4.dp))
                Text(
                    text = stringResource(R.string.free_shipping),
                    style = MaterialTheme.typography.caption,
                    color = MaterialTheme.colors.secondary,
                    maxLines = 1,
                )
            }
        }
    }
}

@Preview(showBackground = true, name = "Product - Theme Light")
@Preview(showBackground = true, name = "Product - Theme Dark", uiMode = UI_MODE_NIGHT_YES)
@Composable
fun DefaultPreview() {
    val product = Product(
        id = "1",
        title = "Smart Tv Samsung Series 8 Un50au8000kxzl Led 4k 50 100v/240v",
        price = 2129900,
        thumbnail = "http://http2.mlstatic.com/D_866962-MLA47916973959_102021-I.jpg",
        shipping = ProductShipping(freeShipping = true, storePickUp = false)
    )

    MeliChallengeTheme {
        ProductView(product) { }
    }
}