package dev.fabirt.melichallenge.ui.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Share
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.rememberPagerState
import dev.fabirt.melichallenge.R
import dev.fabirt.melichallenge.domain.entities.ProductDetail
import dev.fabirt.melichallenge.error.Failure
import dev.fabirt.melichallenge.ui.component.DetailShimmer
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
    val context = LocalContext.current

    LaunchedEffect(itemId) {
        detailViewModel.initialize(itemId)
    }

    Scaffold(
        topBar = {
            TopAppBar(
                onGoBack = { navController.popBackStack() },
                onShare = when(detailResource) {
                    is Resource.Success -> {
                        { detailViewModel.sharePermalink(context, detailResource.data) }
                    }
                    else -> null
                }
            )
        }
    ) {
        when (detailResource) {
            is Resource.Error -> {
                ErrorView(
                    failure = detailResource.failure,
                    onRetry = {
                        detailViewModel.refresh(itemId)
                    }
                )
            }
            Resource.Idle -> {
                DetailShimmer()
            }
            Resource.Loading -> {
                DetailShimmer()
            }
            is Resource.Success -> {
                val detail = detailResource.data
                DetailContent(
                    detail = detail,
                    onBuyNow = { navController.popBackStack() }
                )
            }
        }
    }
}

@Composable
private fun TopAppBar(
    onGoBack: () -> Unit,
    onShare: (() -> Unit)? = null
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
        },
        actions = {
            if (onShare != null) {
                IconButton(onClick = onShare) {
                    Icon(
                        imageVector = Icons.Default.Share,
                        contentDescription = stringResource(R.string.cd_share)
                    )
                }
            }
        }
    )
}

@OptIn(ExperimentalPagerApi::class)
@Composable
private fun DetailContent(
    detail: ProductDetail,
    onBuyNow: () -> Unit
) {
    val scrollState = rememberScrollState()
    val pagerState = rememberPagerState()
    val currentPage = pagerState.currentPage + 1

    Column(
        modifier = Modifier
            .testTag("detail_content")
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
            Box(
                modifier = Modifier
                    .padding(horizontal = 16.dp)
                    .clip(RoundedCornerShape(8.dp))
                    .background(MaterialTheme.colors.onBackground.copy(alpha = 0.08f))
                    .padding(horizontal = 8.dp, vertical = 2.dp),
            ) {
                Text(
                    text = "$currentPage / ${detail.pictures.count()}",
                    style = MaterialTheme.typography.caption.copy(
                        fontWeight = FontWeight.Bold
                    )
                )
            }
            Spacer(modifier = Modifier.height(6.dp))
            HorizontalPager(
                count = detail.pictures.count(),
                state = pagerState
            ) { page ->
                val picture = detail.pictures[page]
                AsyncImage(
                    model = ImageRequest.Builder(LocalContext.current)
                        .data(picture.url)
                        .crossfade(true)
                        .build(),
                    contentDescription = stringResource(R.string.cd_thumbnail),
                    contentScale = ContentScale.Crop,
                    modifier = Modifier
                        .fillMaxWidth()
                        .aspectRatio(3f / 2f)
                )
            }
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
            onClick = onBuyNow,
            modifier = Modifier
                .testTag("buy_button")
                .padding(horizontal = 16.dp)
                .fillMaxWidth(),
            elevation = ButtonDefaults.elevation(0.dp, 0.dp),
        ) {
            Text(stringResource(R.string.buy_now))
        }
        OutlinedButton(
            onClick = onBuyNow,
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

@Composable
private fun ErrorView(
    failure: Failure,
    onRetry: () -> Unit
) {
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        EmphasisText(
            text = failure.translate(),
            textAlign = TextAlign.Center,
            style = MaterialTheme.typography.h6,
            modifier = Modifier.padding(horizontal = 24.dp)
        )
        Spacer(modifier = Modifier.height(8.dp))
        Button(
            onClick = onRetry,
            modifier = Modifier
                .padding(horizontal = 16.dp)
                .fillMaxWidth(),
            elevation = ButtonDefaults.elevation(0.dp, 0.dp),
        ) {
            Text(
                stringResource(R.string.retry),
            )
        }
    }
}