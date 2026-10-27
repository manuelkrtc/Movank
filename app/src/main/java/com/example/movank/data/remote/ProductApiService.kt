package com.example.movank.data.remote

import com.example.movank.data.remote.dto.ProductsResponseDto
import retrofit2.http.GET

interface ProductApiService {
    @GET("products")
    suspend fun getProducts(): ProductsResponseDto
}
