package br.com.alura.aluvery.dao

import androidx.compose.runtime.mutableStateListOf
import br.com.alura.aluvery.model.Product
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.toList

class ProductDao {

    companion object {
        private val products = MutableStateFlow<List<Product>>(emptyList())
    }

    fun products(): StateFlow<List<Product>> = products.asStateFlow()


    fun save(product: Product) {
        products.value = products.value + product
    }

}