package com.example.aluvery.ui.components

import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.aluvery.R
import com.example.aluvery.model.Product
import java.math.BigDecimal

@Composable
fun ProductList(
    title: String,
    products: List<Product>
) {
    Column {
        Text(
            text = title,
            Modifier.padding(
                start = 16.dp,
                end = 16.dp
            ),
            fontSize = 20.sp,
            fontWeight = FontWeight(400)
        )
        Row(
            modifier = Modifier
                .padding(
                    top = 16.dp,
                    bottom = 16.dp
                )
                .fillMaxWidth()
                .horizontalScroll(rememberScrollState())
                .padding(horizontal = 16.dp),
            horizontalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            for (product in products) {
                ProductItem(product = product)
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun ProductListPreview() {
    ProductList("Promoções", sampleProducts)
}

val sampleProducts = listOf(
    Product(
        name = "Hamburguer",
        price = BigDecimal("12.99"),
        image = R.drawable.burger
    ),
    Product(
        name = "Pizza",
        price = BigDecimal("19.99"),
        image = R.drawable.pizza
    ),
    Product(
        name = "Batata frita",
        price = BigDecimal("7.99"),
        image = R.drawable.fries
    )
)