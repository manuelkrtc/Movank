package com.example.movank.domain.usecase

import com.example.movank.domain.model.Product
import com.example.movank.domain.repository.ProductRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow

internal class FakeProductRepository(
    initialProducts: List<Product> = emptyList(),
    var refreshCount: Int = 0,
    var refreshFailure: Exception? = null,
) : ProductRepository {
    private val products = MutableStateFlow(initialProducts)
    var refreshCalls: Int = 0
        private set

    fun emitProducts(value: List<Product>) {
        products.value = value
    }

    override fun observeProducts(): Flow<List<Product>> = products

    override suspend fun refreshProducts(): Int {
        refreshCalls++
        refreshFailure?.let { throw it }
        return refreshCount
    }
}
