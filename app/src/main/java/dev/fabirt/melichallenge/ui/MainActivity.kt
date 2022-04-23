package dev.fabirt.melichallenge.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.platform.LocalTextInputService
import androidx.lifecycle.viewmodel.compose.viewModel
import dagger.hilt.android.AndroidEntryPoint
import dev.fabirt.melichallenge.ui.component.ProductListView
import dev.fabirt.melichallenge.ui.component.SearchBar
import dev.fabirt.melichallenge.ui.model.ProductSearchViewModel
import dev.fabirt.melichallenge.ui.theme.MeliChallengeTheme
import dev.fabirt.melichallenge.util.Resource
import dev.fabirt.melichallenge.util.clearFocus

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val viewModel = viewModel<ProductSearchViewModel>()
            val query by viewModel.query.collectAsState()
            val searchResult = viewModel.productSearch.collectAsState().value

            val focusManager = LocalFocusManager.current
            val textInputService = LocalTextInputService.current

            MeliChallengeTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    Column(
                        modifier = Modifier.fillMaxSize(),
                    ) {
                        SearchBar(
                            value = query,
                            onValueChange = viewModel::changeQuery,
                            onValueClear = viewModel::clearQuery,
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
        }
    }
}
