package com.example.movank.data.remote.dto

import kotlinx.serialization.Serializable

@Serializable
data class ProductDto(
    val id: Int,
    val title: String,
    val price: Double,
    val thumbnail: String,
)
