package com.example.movank.domain.usecase

import com.example.movank.domain.repository.ProductRepository
import javax.inject.Inject

class RefreshProductsUseCase @Inject constructor(
    private val repository: ProductRepository,
) {
    suspend operator fun invoke() = repository.refreshProducts()
}
