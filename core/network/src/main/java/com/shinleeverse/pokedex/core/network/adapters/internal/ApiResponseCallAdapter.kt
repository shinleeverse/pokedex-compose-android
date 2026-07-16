package com.shinleeverse.pokedex.core.network.adapters.internal

import com.shinleeverse.pokedex.core.network.ApiResponse
import retrofit2.Call
import retrofit2.CallAdapter
import java.lang.reflect.Type

/**
 * Tells Retrofit how to adapt standard calls to [ApiResponse].
 */
internal class ApiResponseCallAdapter<R>(
    private val responseType : Type,
) : CallAdapter<R, Call<ApiResponse<R>>> {

    override fun responseType(): Type = responseType

    override fun adapt(call: Call<R>): Call<ApiResponse<R>> = ApiResponseCall(call)
}