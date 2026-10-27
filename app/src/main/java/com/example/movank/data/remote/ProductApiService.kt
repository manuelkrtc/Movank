package com.example.movank.data.remote

import com.example.movank.data.remote.dto.ProductsResponseDto
import retrofit2.http.GET
import retrofit2.http.Query

interface ProductApiService {
    @GET("products")
    suspend fun getProducts(@Query("limit") limit: Int = 20): ProductsResponseDto
}
