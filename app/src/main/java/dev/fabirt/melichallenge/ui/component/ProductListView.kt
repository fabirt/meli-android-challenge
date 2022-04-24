package dev.fabirt.melichallenge.ui.component

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Divider
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.platform.LocalTextInputService
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.unit.dp
import dev.fabirt.melichallenge.domain.entities.Product
import dev.fabirt.melichallenge.util.clearFocus

@Composable
fun ProductListView(
    data: List<Product>,
    showFooterLoader: Boolean,
    onItemClick: (Int, Product) -> Unit,
    onItemAppear: (Int) -> Unit
) {
    val listState = rememberLazyListState()
    val focusManager = LocalFocusManager.current
    val textInputService = LocalTextInputService.current

    LazyColumn(
        state = listState,
        contentPadding = PaddingValues(vertical = 8.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.testTag("item_list")
    ) {
        itemsIndexed(data) { index: Int, item: Product ->
            ProductView(product = item) {
                onItemClick(index, item)
            }

            if (index < data.count() - 1) {
                Divider(
                    modifier = Modifier.padding(horizontal = 8.dp)
                )
            }

            LaunchedEffect(index) {
                onItemAppear(index)
            }
        }

        if (showFooterLoader) item {
            CircularProgressIndicator(
                modifier = Modifier
                    .padding(vertical = 16.dp)
            )
        }
    }

    LaunchedEffect(listState.isScrollInProgress) {
        if (listState.isScrollInProgress) {
            clearFocus(focusManager, textInputService)
        }
    }
}