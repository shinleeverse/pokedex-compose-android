package com.shinleeverse.pokedex.core.network.di

import com.shinleeverse.pokedex.core.network.BuildConfig
import com.shinleeverse.pokedex.core.network.adapters.ApiResponseCallAdapterFactory
import com.shinleeverse.pokedex.core.network.service.PokedexClient
import com.shinleeverse.pokedex.core.network.service.PokedexService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.kotlinx.serialization.asConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
internal object NetworkModule {

    @Singleton
    @Provides
    fun provideJson(): Json = Json {
        ignoreUnknownKeys = true
    }

    @Singleton
    @Provides
    fun provideOkHttpClient(): OkHttpClient = OkHttpClient.Builder().apply {
        if (BuildConfig.DEBUG) {
            this.addNetworkInterceptor(
                HttpLoggingInterceptor().apply {
                    level = HttpLoggingInterceptor.Level.BODY
                }
            )
        }
    }.build()

    @Singleton
    @Provides
    fun provideRetrofit(json: Json, okHttpClient: OkHttpClient): Retrofit =
        Retrofit.Builder()
            .client(okHttpClient)
            .baseUrl("https://pokeapi.co/api/v2")
            .addConverterFactory(json.asConverterFactory("application/json".toMediaType()))
            .addCallAdapterFactory(ApiResponseCallAdapterFactory.create())
            .build()

    @Singleton
    @Provides
    fun providePokedexService(retrofit: Retrofit): PokedexService =
        retrofit.create(PokedexService::class.java)

    @Singleton
    @Provides
    fun providePokedexClient(pokedexService: PokedexService): PokedexClient =
        PokedexClient(pokedexService)
}