package com.example.movank.presentation.products

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle

@Composable
fun ProductListRoute(modifier: Modifier = Modifier) {
    val viewModel: ProductListViewModel = hiltViewModel()
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    ProductListScreen(
        uiState = uiState,
        onRetry = viewModel::retry,
        modifier = modifier,
    )
}
