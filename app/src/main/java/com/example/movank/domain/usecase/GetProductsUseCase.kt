package com.example.movank.domain.usecase

import com.example.movank.domain.model.Product
import com.example.movank.domain.repository.ProductRepository
import javax.inject.Inject

class GetProductsUseCase @Inject constructor(
    private val repository: ProductRepository,
) {
    suspend operator fun invoke(): List<Product> = repository.getProducts()
}
