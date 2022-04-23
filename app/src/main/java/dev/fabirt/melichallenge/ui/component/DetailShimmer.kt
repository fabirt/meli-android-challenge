package dev.fabirt.melichallenge.ui.component

import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.OutlinedButton
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.google.accompanist.placeholder.material.placeholder
import dev.fabirt.melichallenge.R
import dev.fabirt.melichallenge.ui.theme.MeliChallengeTheme

@Composable
fun DetailShimmer() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(vertical = 16.dp)
    ) {
        Text(
            text = stringResource(R.string.sold_quantity, 0),
            style = MaterialTheme.typography.caption,
            modifier = Modifier
                .padding(horizontal = 16.dp)
                .placeholder(visible = true)
        )
        Spacer(modifier = Modifier.height(4.dp))
        Text(
            text = "post detail title placeholder",
            style = MaterialTheme.typography.body1,
            modifier = Modifier
                .padding(horizontal = 16.dp)
                .placeholder(visible = true)
        )
        Spacer(modifier = Modifier.height(16.dp))
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .aspectRatio(3f / 2f)
                .placeholder(visible = true)
        )
        Spacer(modifier = Modifier.height(24.dp))
        Text(
            text = "$ 000,000,000",
            style = MaterialTheme.typography.h4,
            modifier = Modifier
                .padding(horizontal = 16.dp)
                .placeholder(visible = true),
        )
        Spacer(modifier = Modifier.height(4.dp))
        Text(
            text = stringResource(R.string.free_shipping),
            style = MaterialTheme.typography.body2,
            color = MaterialTheme.colors.secondary,
            maxLines = 1,
            modifier = Modifier
                .padding(horizontal = 16.dp)
                .placeholder(visible = true),
        )

        Spacer(modifier = Modifier.height(16.dp))
        Button(
            onClick = { },
            enabled = false,
            modifier = Modifier
                .padding(horizontal = 16.dp)
                .fillMaxWidth()
                .placeholder(visible = true),
        ) {
            Text(stringResource(R.string.buy_now))
        }
        Spacer(modifier = Modifier.height(8.dp))
        OutlinedButton(
            onClick = {  },
            enabled = false,
            modifier = Modifier
                .padding(horizontal = 16.dp)
                .fillMaxWidth()
                .placeholder(visible = true),
        ) {
            Text(stringResource(R.string.add_to_cart))
        }
    }
}

@Preview(showBackground = true, name = "Detail shimmer")
@Composable
private fun DetailShimmerPreview() {
    MeliChallengeTheme {
        DetailShimmer()
    }
}