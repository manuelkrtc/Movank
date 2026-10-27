package com.example.movank.di

import com.example.movank.data.repository.ProductRepositoryImpl
import com.example.movank.domain.repository.ProductRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {
    @Binds
    abstract fun bindProductRepository(impl: ProductRepositoryImpl): ProductRepository
}
