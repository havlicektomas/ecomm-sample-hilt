package dev.havlicektomas.ecommhilt.data

import android.util.Log
import dev.havlicektomas.ecommhilt.domain.Product
import dev.havlicektomas.ecommhilt.domain.ProductRepo
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class DefaultProductRepo @Inject constructor(
    private val productApi: ProductApi
): ProductRepo {

    override fun loadProducts(): Flow<List<Product>> {
        return flow {
            val response = productApi.getProducts(20)
            if (response.isSuccessful) {
                response.body()?.let {
                    emit(it)
                }
            } else {
                Log.d("DefaultProductRepo", "loadProducts failed")
            }
        }
    }
}