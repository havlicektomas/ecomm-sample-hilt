package dev.havlicektomas.ecommhilt.di

import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import dev.havlicektomas.ecommhilt.data.DefaultProductRepo
import dev.havlicektomas.ecommhilt.data.ProductApi
import dev.havlicektomas.ecommhilt.domain.ProductRepo
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.create
import retrofit2.converter.moshi.MoshiConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideProductApi(): ProductApi {
        val okHttpClient = OkHttpClient.Builder()
                .build()

        val moshi = Moshi.Builder()
            .add(KotlinJsonAdapterFactory())
            .build()

        return Retrofit.Builder()
            .baseUrl(ProductApi.BASE_URL)
            .addConverterFactory(MoshiConverterFactory.create(moshi))
            .client(okHttpClient)
            .build()
            .create()
    }

    @Provides
    @Singleton
    fun provideProductRepo(
        productApi: ProductApi
    ): ProductRepo {
        return DefaultProductRepo(
            productApi = productApi
        )
    }
}