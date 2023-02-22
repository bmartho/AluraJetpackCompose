package br.com.alura.aluvery.dao

import androidx.compose.runtime.mutableStateListOf
import br.com.alura.aluvery.model.Product
import br.com.alura.aluvery.sampledata.sampleProducts

class ProductDao {

    companion object {
        private val products = mutableStateListOf(*sampleProducts.toTypedArray())
    }

    fun products() = products.toList()

    fun save(product: Product) {
        products.add(product)
    }
}