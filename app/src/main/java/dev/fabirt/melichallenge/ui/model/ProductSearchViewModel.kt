package dev.fabirt.melichallenge.ui.model

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import dev.fabirt.melichallenge.domain.entities.ProductSearchResult
import dev.fabirt.melichallenge.domain.repository.MeliRepository
import dev.fabirt.melichallenge.util.Debouncer
import dev.fabirt.melichallenge.util.Resource
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ProductSearchViewModel @Inject constructor(
    private val repository: MeliRepository
) : ViewModel() {

    private val _query = MutableStateFlow("")
    val query: StateFlow<String> get() = _query

    private val _productSearch = MutableStateFlow<Resource<ProductSearchResult>>(Resource.Idle)
    val productSearch: StateFlow<Resource<ProductSearchResult>> get() = _productSearch

    private val _pagingState = MutableStateFlow<Resource<Nothing>>(Resource.Idle)
    val pagingState: StateFlow<Resource<Nothing>> get() = _pagingState

    private val searchDebouncer = Debouncer(300)

    fun changeQuery(value: String) {
        _query.value = value
        searchDebouncer.launch(viewModelScope) {
            searchProduct(value)
        }
    }

    fun clearQuery() {
        _query.value = ""
    }

    private suspend fun searchProduct(query: String) {
        val result = repository.searchProduct(query, 10, 0)
        result.fold(
            { _productSearch.value = Resource.Error(it) },
            { _productSearch.value = Resource.Success(it) }
        )
    }

    private fun loadMore(itemIndex: Int) {
        val currentResult = _productSearch.value as? Resource.Success ?: return
        val products = currentResult.data.results.toMutableList()

        val thresholdIndex = products.count() - 2
        if (itemIndex != thresholdIndex) return

        _pagingState.value = Resource.Loading

        viewModelScope.launch {
            val result = repository.searchProduct(_query.value, 10, 0)
            result.fold(
                { _pagingState.value = Resource.Error(it) },
                { newResult ->
                    products.addAll(newResult.results)
                    _pagingState.value = Resource.Idle
                    _productSearch.value = Resource.Success(
                        newResult.copy(results = products)
                    )
                }
            )
        }
    }
}