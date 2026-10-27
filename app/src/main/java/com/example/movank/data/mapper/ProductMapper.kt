package com.example.movank.data.mapper

import com.example.movank.data.remote.dto.ProductDto
import com.example.movank.domain.model.Product

fun ProductDto.toDomain(): Product = Product(
    id = id,
    name = title,
    price = price,
    imageUrl = thumbnail,
)
