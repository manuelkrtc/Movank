package com.example.movank.data.remote.dto

import kotlinx.serialization.Serializable

@Serializable
data class ProductsResponseDto(
    val products: List<ProductDto>,
)
