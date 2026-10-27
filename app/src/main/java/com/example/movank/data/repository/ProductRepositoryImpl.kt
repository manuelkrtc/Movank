package com.example.movank.data.repository

import com.example.movank.data.mapper.toDomain
import com.example.movank.data.remote.ProductApiService
import com.example.movank.domain.model.Product
import com.example.movank.domain.repository.ProductRepository
import javax.inject.Inject

class ProductRepositoryImpl @Inject constructor(
    private val apiService: ProductApiService,
) : ProductRepository {
    override suspend fun getProducts(): List<Product> =
        apiService.getProducts().products.map { it.toDomain() }
}
