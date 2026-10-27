package com.example.movank.domain.usecase

import com.example.movank.domain.model.Product
import kotlinx.coroutines.CoroutineStart
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.take
import kotlinx.coroutines.flow.toList
import kotlinx.coroutines.launch
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Test

class ObserveProductsUseCaseTest {
    private val product = Product(1, "Product", 9.99, "https://example.com/product.png")

    @Test
    fun returnsProductsEmittedByRepository() = runTest {
        val repository = FakeProductRepository(initialProducts = listOf(product))

        assertEquals(listOf(product), ObserveProductsUseCase(repository)().first())
    }

    @Test
    fun propagatesSubsequentProductChanges() = runTest {
        val repository = FakeProductRepository(initialProducts = listOf(product))
        val updated = listOf(product, product.copy(id = 2, name = "Second Product"))
        val emissions = mutableListOf<List<Product>>()
        val collection = backgroundScope.launch(start = CoroutineStart.UNDISPATCHED) {
            ObserveProductsUseCase(repository)().take(2).toList(emissions)
        }

        repository.emitProducts(updated)
        collection.join()

        assertEquals(listOf(listOf(product), updated), emissions)
    }

    @Test
    fun emitsEmptyListWhenRepositoryIsEmpty() = runTest {
        val repository = FakeProductRepository()

        assertEquals(emptyList<Product>(), ObserveProductsUseCase(repository)().first())
    }
}
