package com.example.movank.data.local

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Upsert
import kotlinx.coroutines.flow.Flow

@Dao
interface ProductDao {
    @Query("SELECT * FROM products ORDER BY id ASC")
    fun observeProducts(): Flow<List<ProductEntity>>

    @Upsert
    suspend fun upsertProducts(products: List<ProductEntity>)

    @Query("DELETE FROM products")
    suspend fun deleteAllProducts()
}
