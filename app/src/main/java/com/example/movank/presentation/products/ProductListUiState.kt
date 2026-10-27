package com.example.movank.presentation.products

import com.example.movank.domain.model.Product

sealed interface ProductListUiState {
    data object Loading : ProductListUiState

    data class Success(val products: List<Product>) : ProductListUiState

    data object Empty : ProductListUiState

    data class Error(val message: String) : ProductListUiState
}
