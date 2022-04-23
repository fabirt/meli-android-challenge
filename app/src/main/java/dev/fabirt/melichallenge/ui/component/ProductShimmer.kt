package dev.fabirt.melichallenge.ui.component

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.google.accompanist.placeholder.material.placeholder
import dev.fabirt.melichallenge.R

@Composable
fun ProductShimmer() {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier.padding(16.dp)
    ) {
        Box(
            modifier = Modifier
                .size(88.dp)
                .clip(RoundedCornerShape(8.dp))
                .placeholder(visible = true)
        )
        Spacer(modifier = Modifier.width(16.dp))

        Column(
            modifier = Modifier.weight(1f)
        ) {
            Text(
                "",
                modifier = Modifier.placeholder(visible = true).fillMaxWidth(),
                maxLines = 2,
            )
            Spacer(modifier = Modifier.height(4.dp))
            Text(
                "$ 00,000,000,",
                modifier = Modifier.placeholder(visible = true),
                style = MaterialTheme.typography.h6,
                maxLines = 1,
            )
            Spacer(modifier = Modifier.height(4.dp))
            Text(
                text = stringResource(R.string.free_shipping),
                style = MaterialTheme.typography.caption,
                color = MaterialTheme.colors.secondary,
                maxLines = 1,
                modifier = Modifier.placeholder(visible = true),
            )
        }
    }
}