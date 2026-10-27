package com.example.movank.data.local

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [ProductEntity::class], version = 1, exportSchema = true)
abstract class ProductDatabase : RoomDatabase() {
    abstract fun productDao(): ProductDao
}
