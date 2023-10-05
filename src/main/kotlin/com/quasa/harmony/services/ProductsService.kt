package com.quasa.harmony.services

import com.quasa.harmony.generated.types.Product
import org.springframework.stereotype.Service


interface ProductsService {
    fun products(): List<Product>
}

/**
 * This service gives a fixed in-memory collection of Shows.
 * In a more realistic implementation the Shows could be loaded from a datastore.
 */
 @Service
 class BasicProductsService: ProductsService {
    override fun products(): List<Product> {
        return listOf()
    }
 }