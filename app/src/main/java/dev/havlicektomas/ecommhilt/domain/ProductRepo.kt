package dev.havlicektomas.ecommhilt.domain

import kotlinx.coroutines.flow.Flow

interface ProductRepo {
    fun loadProducts(): Flow<List<Product>>
}