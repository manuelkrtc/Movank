package com.example.movank.domain.usecase

import com.example.movank.domain.model.Product
import com.example.movank.domain.repository.ProductRepository
import javax.inject.Inject
import kotlinx.coroutines.flow.Flow

class ObserveProductsUseCase @Inject constructor(
    private val repository: ProductRepository,
) {
    operator fun invoke(): Flow<List<Product>> = repository.observeProducts()
}
