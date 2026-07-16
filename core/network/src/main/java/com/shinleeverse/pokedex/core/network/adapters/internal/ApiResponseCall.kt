package com.shinleeverse.pokedex.core.network.adapters.internal

import com.shinleeverse.pokedex.core.network.ApiResponse
import com.shinleeverse.pokedex.core.network.toApiResponse
import okhttp3.Request
import okio.Timeout
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

/**
 * Intercepts Retrofit calls and maps them to [ApiResponse].
 */
internal class ApiResponseCall<T>(
    private val delegate: Call<T>,
) : Call<ApiResponse<T>> {

    override fun execute(): Response<ApiResponse<T>?> = Response.success(delegate.execute().toApiResponse())

    override fun enqueue(callback: Callback<ApiResponse<T>>) {
        delegate.enqueue(object : Callback<T> {
            override fun onResponse(call: Call<T?>, response: Response<T>) {
                callback.onResponse(this@ApiResponseCall, Response.success(response.toApiResponse()))
            }

            override fun onFailure(call: Call<T?>, t: Throwable) {
                callback.onResponse(
                    this@ApiResponseCall,
                    Response.success((ApiResponse.Exception(t)))
                )
            }
        })
    }

    override fun isExecuted(): Boolean = delegate.isExecuted
    override fun cancel() = delegate.cancel()
    override fun isCanceled(): Boolean = delegate.isCanceled
    override fun clone(): Call<ApiResponse<T>> = ApiResponseCall(delegate.clone())
    override fun request(): Request = delegate.request()
    override fun timeout(): Timeout = delegate.timeout()
}