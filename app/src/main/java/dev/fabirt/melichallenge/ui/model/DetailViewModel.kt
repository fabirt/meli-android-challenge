package dev.fabirt.melichallenge.ui.model

import android.content.Context
import android.content.Intent
import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import dev.fabirt.melichallenge.domain.entities.ProductDetail
import dev.fabirt.melichallenge.domain.repository.MeliRepository
import dev.fabirt.melichallenge.util.Resource
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DetailViewModel @Inject constructor(
    private val repository: MeliRepository
) : ViewModel() {

    private val _detail = MutableStateFlow<Resource<ProductDetail>>(Resource.Idle)
    val detail: StateFlow<Resource<ProductDetail>> get() = _detail

    fun initialize(id: String) {
        Log.d("DetailViewModel", "initialize called for item $id")
        viewModelScope.launch {
            val result = repository.searchDetail(id)
            result.fold(
                { _detail.value = Resource.Error(it) },
                { _detail.value = Resource.Success(it) }
            )
        }
    }

    fun refresh(id: String) {
        _detail.value = Resource.Loading
        initialize(id)
    }

    fun sharePermalink(context: Context, detail: ProductDetail) {
        val sendIntent = Intent().apply {
            action = Intent.ACTION_SEND
            putExtra(Intent.EXTRA_TITLE, detail.title)
            putExtra(Intent.EXTRA_TEXT, detail.permalink)
            type = "text/plain"
        }

        val shareIntent = Intent.createChooser(sendIntent, null)
        context.startActivity(shareIntent)
    }
}