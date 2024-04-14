package dev.havlicektomas.ecommhilt.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import dev.havlicektomas.ecommhilt.domain.Product
import dev.havlicektomas.ecommhilt.domain.ProductRepo
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import javax.inject.Inject

@HiltViewModel
class ProductViewModel @Inject constructor(
    private val productRepo: ProductRepo
): ViewModel() {

    val state: StateFlow<List<Product>> = productRepo
        .loadProducts()
        .stateIn(
            viewModelScope,
            started = SharingStarted.WhileSubscribed(5000L),
            emptyList()
        )
}