package com.example.movank.presentation.products.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil3.compose.AsyncImage
import com.example.movank.domain.model.Product
import com.example.movank.ui.theme.MovankTheme
import java.text.NumberFormat
import java.util.Locale

@Composable
fun ProductCard(
    product: Product,
    modifier: Modifier = Modifier,
) {
    val price = remember(product.price) {
        NumberFormat.getCurrencyInstance(Locale.US).format(product.price)
    }

    Card(modifier = modifier) {
        Row(
            modifier = Modifier.padding(12.dp),
            verticalAlignment = Alignment.CenterVertically,
        ) {
            AsyncImage(
                model = product.imageUrl.takeIf { it.isNotBlank() },
                contentDescription = "Image of ${product.name}",
                modifier = Modifier
                    .size(96.dp)
                    .clip(RoundedCornerShape(8.dp)),
                contentScale = ContentScale.Crop,
                placeholder = painterResource(android.R.drawable.ic_menu_gallery),
                error = painterResource(android.R.drawable.ic_menu_report_image),
                fallback = painterResource(android.R.drawable.ic_menu_gallery),
            )

            Spacer(modifier = Modifier.width(12.dp))

            Column(verticalArrangement = Arrangement.spacedBy(4.dp)) {
                Text(text = product.name, style = MaterialTheme.typography.titleMedium)
                Text(text = price, style = MaterialTheme.typography.bodyMedium)
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun ProductCardPreview() {
    MovankTheme(dynamicColor = false) {
        ProductCard(
            product = Product(
                id = 1,
                name = "Sample Product",
                price = 29.99,
                imageUrl = "",
            ),
        )
    }
}
