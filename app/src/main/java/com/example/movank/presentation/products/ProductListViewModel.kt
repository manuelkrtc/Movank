package com.example.movank.presentation.products

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.movank.domain.usecase.ObserveProductsUseCase
import com.example.movank.domain.usecase.RefreshProductsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.CancellationException
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

@HiltViewModel
class ProductListViewModel @Inject constructor(
    observeProductsUseCase: ObserveProductsUseCase,
    private val refreshProductsUseCase: RefreshProductsUseCase,
) : ViewModel() {
    private sealed interface RefreshState {
        data object Loading : RefreshState
        data class Complete(val productCount: Int) : RefreshState
        data class Failed(val message: String) : RefreshState
    }

    private val refreshState = MutableStateFlow<RefreshState>(RefreshState.Loading)
    private var refreshJob: Job? = null

    val uiState: StateFlow<ProductListUiState> =
        combine(observeProductsUseCase(), refreshState) { products, refresh ->
            if (products.isNotEmpty()) {
                ProductListUiState.Success(products)
            } else {
                when (refresh) {
                    RefreshState.Loading -> ProductListUiState.Loading
                    is RefreshState.Complete -> if (refresh.productCount == 0) {
                        ProductListUiState.Empty
                    } else {
                        ProductListUiState.Loading
                    }
                    is RefreshState.Failed -> ProductListUiState.Error(refresh.message)
                }
            }
        }.stateIn(viewModelScope, SharingStarted.Eagerly, ProductListUiState.Loading)

    init {
        retry()
    }

    fun retry() {
        if (refreshJob?.isActive == true) return

        refreshState.value = RefreshState.Loading
        refreshJob = viewModelScope.launch {
            try {
                val productCount = refreshProductsUseCase()
                refreshState.value = RefreshState.Complete(productCount)
            } catch (exception: CancellationException) {
                throw exception
            } catch (exception: Exception) {
                refreshState.value = RefreshState.Failed(
                    exception.message?.takeIf { it.isNotBlank() } ?: "Unable to load products.",
                )
            }
        }
    }
}
