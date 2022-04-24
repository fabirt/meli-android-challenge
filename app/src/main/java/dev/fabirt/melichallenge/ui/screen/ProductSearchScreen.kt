package dev.fabirt.melichallenge.ui.screen

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.platform.LocalTextInputService
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import dev.fabirt.melichallenge.R
import dev.fabirt.melichallenge.ui.component.ProductListView
import dev.fabirt.melichallenge.ui.component.ProductShimmer
import dev.fabirt.melichallenge.ui.component.SearchBar
import dev.fabirt.melichallenge.ui.model.ProductSearchViewModel
import dev.fabirt.melichallenge.ui.navigation.Destination
import dev.fabirt.melichallenge.ui.navigation.Navigator
import dev.fabirt.melichallenge.util.Resource
import dev.fabirt.melichallenge.util.clearFocus

@Composable
fun ProductSearchScreen() {
    val searchViewModel = hiltViewModel<ProductSearchViewModel>()
    val query by searchViewModel.query.collectAsState()
    val canLoadMore by searchViewModel.canLoadMore.collectAsState()
    val searchResult = searchViewModel.productSearch.collectAsState().value

    val focusManager = LocalFocusManager.current
    val textInputService = LocalTextInputService.current
    val navController = Navigator.current

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
                onDone = { clearFocus(focusManager, textInputService) },
                modifier = Modifier.testTag("search_input")
            )
            when (searchResult) {
                is Resource.Error -> {
                    NoDataText(searchResult.failure.translate())
                }
                Resource.Idle -> {
                    NoDataText(stringResource(R.string.empty_search_result))
                }
                Resource.Loading -> {
                    ProductLoader()
                }
                is Resource.Success -> {
                    if (searchResult.data.results.isEmpty()) {
                        NoResultFor(searchResult.data.query)
                    } else {
                        ProductListView(
                            data = searchResult.data.results,
                            showFooterLoader = canLoadMore,
                            onItemClick = { _, p ->
                                navController.navigate(
                                    Destination.DETAIL.replace("{id}", p.id)
                                )
                            },
                            onItemAppear = searchViewModel::loadMore
                        )
                    }
                }
            }
        }
    }
}

@Composable
fun NoDataText(text: String) {
    Text(
        text = text,
        modifier = Modifier.padding(horizontal = 16.dp, vertical = 8.dp)
    )
}

@Composable
fun NoResultFor(text: String) {
    val builder = AnnotatedString.Builder()
    val boldText = "\"$text\""
    val contentText = stringResource(R.string.no_result_for, boldText)
    builder.append(contentText)
    val start = contentText.indexOf(boldText)
    val end = start + boldText.length
    val queryStyle = SpanStyle(
        fontWeight = FontWeight.Bold
    )
    builder.addStyle(queryStyle, start, end)
    Text(
        text = builder.toAnnotatedString(),
        modifier = Modifier.padding(horizontal = 16.dp, vertical = 8.dp)
    )
}

@Composable
fun ProductLoader() {
    Column(
        modifier = Modifier.padding(top = 8.dp)
    ) {
        for (i in 1..10) {
            ProductShimmer()
        }
    }
}