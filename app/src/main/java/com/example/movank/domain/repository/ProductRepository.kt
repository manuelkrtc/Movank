package com.example.movank.domain.repository

import com.example.movank.domain.model.Product

interface ProductRepository {
    suspend fun getProducts(): List<Product>
}
