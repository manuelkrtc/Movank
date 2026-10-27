package com.example.movank.presentation.products

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.movank.domain.model.Product
import com.example.movank.presentation.products.components.ProductCard
import com.example.movank.ui.theme.MovankTheme

@Composable
fun ProductListScreen(
    uiState: ProductListUiState,
    onRetry: () -> Unit,
    modifier: Modifier = Modifier,
) {
    when (uiState) {
        ProductListUiState.Loading -> {
            Box(modifier = modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                CircularProgressIndicator()
            }
        }

        ProductListUiState.Empty -> {
            Box(modifier = modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                Text(text = "No products available.")
            }
        }

        is ProductListUiState.Error -> {
            Box(modifier = modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                Column(
                    modifier = Modifier.padding(24.dp),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.spacedBy(16.dp),
                ) {
                    Text(
                        text = uiState.message,
                        style = MaterialTheme.typography.bodyLarge,
                        textAlign = TextAlign.Center,
                    )
                    Button(onClick = onRetry) {
                        Text(text = "Retry")
                    }
                }
            }
        }

        is ProductListUiState.Success -> {
            LazyColumn(
                modifier = modifier.fillMaxSize(),
                contentPadding = PaddingValues(16.dp),
                verticalArrangement = Arrangement.spacedBy(12.dp),
            ) {
                items(uiState.products, key = { it.id }) { product ->
                    ProductCard(product = product, modifier = Modifier.fillMaxWidth())
                }
            }
        }
    }
}

@Preview(showBackground = true, widthDp = 360, heightDp = 640)
@Composable
private fun LoadingPreview() {
    MovankTheme(dynamicColor = false) {
        ProductListScreen(uiState = ProductListUiState.Loading, onRetry = {})
    }
}

@Preview(showBackground = true, widthDp = 360, heightDp = 640)
@Composable
private fun SuccessPreview() {
    MovankTheme(dynamicColor = false) {
        ProductListScreen(
            uiState = ProductListUiState.Success(
                products = listOf(
                    Product(id = 1, name = "Sample Product", price = 29.99, imageUrl = ""),
                    Product(id = 2, name = "Another Product", price = 49.50, imageUrl = ""),
                ),
            ),
            onRetry = {},
        )
    }
}

@Preview(showBackground = true, widthDp = 360, heightDp = 640)
@Composable
private fun EmptyPreview() {
    MovankTheme(dynamicColor = false) {
        ProductListScreen(uiState = ProductListUiState.Empty, onRetry = {})
    }
}

@Preview(showBackground = true, widthDp = 360, heightDp = 640)
@Composable
private fun ErrorPreview() {
    MovankTheme(dynamicColor = false) {
        ProductListScreen(
            uiState = ProductListUiState.Error("Unable to load products."),
            onRetry = {},
        )
    }
}
