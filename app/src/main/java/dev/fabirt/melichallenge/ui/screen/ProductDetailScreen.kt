package dev.fabirt.melichallenge.ui.screen

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.AsyncImage
import coil.request.ImageRequest
import dev.fabirt.melichallenge.R
import dev.fabirt.melichallenge.domain.entities.ProductDetail
import dev.fabirt.melichallenge.ui.component.EmphasisText
import dev.fabirt.melichallenge.ui.model.DetailViewModel
import dev.fabirt.melichallenge.ui.navigation.Navigator
import dev.fabirt.melichallenge.util.Resource
import dev.fabirt.melichallenge.util.toCurrencyString

@Composable
fun ProductDetailScreen(itemId: String) {
    val navController = Navigator.current
    val detailViewModel = hiltViewModel<DetailViewModel>()
    val detailResource = detailViewModel.detail.collectAsState().value

    LaunchedEffect(itemId) {
        detailViewModel.initialize(itemId)
    }

    Scaffold(
        topBar = {
            TopAppBar { navController.popBackStack() }
        }
    ) {
        when (detailResource) {
            is Resource.Error -> {}
            Resource.Idle -> {}
            Resource.Loading -> {}
            is Resource.Success -> {
                val detail = detailResource.data
                DetailContent(detail)
            }
        }
    }
}

@Composable
private fun TopAppBar(
    onGoBack: () -> Unit
) {
    TopAppBar(
        title = {
            Text(stringResource(R.string.detail))
        },
        navigationIcon = {
            IconButton(onClick = onGoBack) {
                Icon(
                    imageVector = Icons.Default.ArrowBack,
                    contentDescription = stringResource(
                        R.string.cd_back
                    )
                )
            }
        }
    )
}

@Composable
fun DetailContent(detail: ProductDetail) {
    val scrollState = rememberScrollState()
    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(scrollState)
            .padding(vertical = 16.dp)
    ) {
        if (detail.soldQuantity > 1) {
            EmphasisText(
                text = stringResource(R.string.sold_quantity, detail.soldQuantity),
                style = MaterialTheme.typography.caption,
                modifier = Modifier.padding(horizontal = 16.dp)
            )
            Spacer(modifier = Modifier.height(4.dp))
        }
        Text(
            text = detail.title,
            style = MaterialTheme.typography.body1,
            modifier = Modifier.padding(horizontal = 16.dp)
        )
        if (detail.subtitle != null) {
            Spacer(modifier = Modifier.height(4.dp))
            Text(
                text = detail.subtitle,
                style = MaterialTheme.typography.body2,
                modifier = Modifier.padding(horizontal = 16.dp)
            )
        }
        Spacer(modifier = Modifier.height(16.dp))
        if (detail.hasPictures) {

        } else {
            AsyncImage(
                model = ImageRequest.Builder(LocalContext.current)
                    .data(detail.thumbnail)
                    .crossfade(true)
                    .build(),
                contentDescription = stringResource(R.string.cd_thumbnail),
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .fillMaxWidth()
                    .aspectRatio(3f / 2f)
            )
        }
        Spacer(modifier = Modifier.height(24.dp))
        if (detail.hasDiscount) {
            EmphasisText(
                text = detail.basePrice.toCurrencyString(),
                style = MaterialTheme.typography.body1,
                modifier = Modifier.padding(horizontal = 16.dp),
                textDecoration = TextDecoration.LineThrough
            )
        }
        Text(
            text = detail.price.toCurrencyString(),
            style = MaterialTheme.typography.h4,
            modifier = Modifier.padding(horizontal = 16.dp),
        )
        if (detail.shipping.freeShipping) {
            Spacer(modifier = Modifier.height(4.dp))
            Text(
                text = stringResource(R.string.free_shipping),
                style = MaterialTheme.typography.body2,
                color = MaterialTheme.colors.secondary,
                maxLines = 1,
                modifier = Modifier.padding(horizontal = 16.dp),
            )
        }

        Spacer(modifier = Modifier.height(16.dp))
        Button(
            onClick = { },
            modifier = Modifier
                .padding(horizontal = 16.dp)
                .fillMaxWidth(),
            elevation = ButtonDefaults.elevation(0.dp, 0.dp),
        ) {
            Text(stringResource(R.string.buy_now))
        }
        OutlinedButton(
            onClick = { },
            modifier = Modifier
                .padding(horizontal = 16.dp)
                .fillMaxWidth(),
            colors = ButtonDefaults.outlinedButtonColors(
                backgroundColor = MaterialTheme.colors.background
            )
        ) {
            Text(stringResource(R.string.add_to_cart))
        }
    }
}