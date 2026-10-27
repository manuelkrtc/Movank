package com.example.movank.data.repository

import com.example.movank.data.local.ProductDao
import com.example.movank.data.mapper.toDomain
import com.example.movank.data.remote.ProductApiService
import com.example.movank.domain.model.Product
import com.example.movank.domain.repository.ProductRepository
import javax.inject.Inject
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class ProductRepositoryImpl @Inject constructor(
    private val apiService: ProductApiService,
    private val productDao: ProductDao,
) : ProductRepository {
    override suspend fun getProducts(): List<Product> =
        apiService.getProducts().products.map { it.toDomain() }

    override fun observeProducts(): Flow<List<Product>> =
        productDao.observeProducts().map { entities -> entities.map { it.toDomain() } }
}
