package dev.fabirt.melichallenge.ui.screen

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.platform.LocalTextInputService
import androidx.lifecycle.viewmodel.compose.viewModel
import dev.fabirt.melichallenge.ui.component.ProductListView
import dev.fabirt.melichallenge.ui.component.SearchBar
import dev.fabirt.melichallenge.ui.model.ProductSearchViewModel
import dev.fabirt.melichallenge.util.Resource
import dev.fabirt.melichallenge.util.clearFocus

@Composable
fun ProductSearchScreen() {
    val searchViewModel = viewModel<ProductSearchViewModel>()
    val query by searchViewModel.query.collectAsState()
    val searchResult = searchViewModel.productSearch.collectAsState().value

    val focusManager = LocalFocusManager.current
    val textInputService = LocalTextInputService.current

    Surface(
        modifier = Modifier.fillMaxSize(),
        color = MaterialTheme.colors.background
    ) {
        Column(
            modifier = Modifier.fillMaxSize(),
        ) {
            SearchBar(
                value = query,
                onValueChange = searchViewModel::changeQuery,
                onValueClear = searchViewModel::clearQuery,
                onDone = { clearFocus(focusManager, textInputService) }
            )
            when (searchResult) {
                is Resource.Error -> { }
                Resource.Idle -> { }
                Resource.Loading -> { }
                is Resource.Success -> {
                    ProductListView(
                        data = searchResult.data.results,
                        onItemClick = { _, _ -> }
                    )
                }
            }
        }
    }
}