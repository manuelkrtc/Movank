package com.example.movank.domain.usecase

import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Assert.assertSame
import org.junit.Assert.assertThrows
import org.junit.Test

class RefreshProductsUseCaseTest {
    @Test
    fun callsRepositoryRefreshExactlyOnce() = runTest {
        val repository = FakeProductRepository()

        RefreshProductsUseCase(repository)()

        assertEquals(1, repository.refreshCalls)
    }

    @Test
    fun returnsRepositoryProductCount() = runTest {
        val repository = FakeProductRepository(refreshCount = 20)

        assertEquals(20, RefreshProductsUseCase(repository)())
    }

    @Test
    fun propagatesRepositoryExceptions() {
        val failure = IllegalStateException("Refresh failed")
        val repository = FakeProductRepository(refreshFailure = failure)

        val thrown = assertThrows(IllegalStateException::class.java) {
            runTest { RefreshProductsUseCase(repository)() }
        }

        assertSame(failure, thrown)
    }
}
