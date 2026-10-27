package com.example.movank.presentation.products

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.movank.domain.usecase.GetProductsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.CancellationException
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

@HiltViewModel
class ProductListViewModel @Inject constructor(
    private val getProductsUseCase: GetProductsUseCase,
) : ViewModel() {
    private val _uiState = MutableStateFlow<ProductListUiState>(ProductListUiState.Loading)
    val uiState: StateFlow<ProductListUiState> = _uiState.asStateFlow()

    init {
        loadProducts()
    }

    fun retry() {
        loadProducts()
    }

    private fun loadProducts() {
        _uiState.value = ProductListUiState.Loading
        viewModelScope.launch {
            try {
                val products = getProductsUseCase()
                _uiState.value = if (products.isEmpty()) {
                    ProductListUiState.Empty
                } else {
                    ProductListUiState.Success(products)
                }
            } catch (exception: CancellationException) {
                throw exception
            } catch (exception: Exception) {
                _uiState.value = ProductListUiState.Error(
                    exception.message?.takeIf { it.isNotBlank() } ?: "Unable to load products.",
                )
            }
        }
    }
}
