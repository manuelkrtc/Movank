package com.example.movank.domain.repository

import com.example.movank.domain.model.Product
import kotlinx.coroutines.flow.Flow

interface ProductRepository {
    suspend fun getProducts(): List<Product>
    fun observeProducts(): Flow<List<Product>>
    suspend fun refreshProducts()
}
