package dev.havlicektomas.ecommhilt.data

import dev.havlicektomas.ecommhilt.domain.Product
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface ProductApi {

    @GET("products")
    suspend fun getProducts(
        @Query("limit") limit: Int
    ): Response<List<Product>>

    companion object {
        const val BASE_URL = "https://fakestoreapi.com/"
    }
}