package com.example.movank.di

import android.content.Context
import androidx.room.Room
import com.example.movank.data.local.ProductDao
import com.example.movank.data.local.ProductDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {
    private const val DATABASE_NAME = "products.db"

    @Provides
    @Singleton
    fun provideProductDatabase(@ApplicationContext context: Context): ProductDatabase =
        Room.databaseBuilder(context, ProductDatabase::class.java, DATABASE_NAME).build()

    @Provides
    fun provideProductDao(database: ProductDatabase): ProductDao = database.productDao()
}
