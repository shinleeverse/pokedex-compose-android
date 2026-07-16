package com.shinleeverse.pokedex.core.network.adapters

import com.shinleeverse.pokedex.core.network.ApiResponse
import com.shinleeverse.pokedex.core.network.adapters.internal.ApiResponseCallAdapter
import retrofit2.Call
import retrofit2.CallAdapter
import retrofit2.Retrofit
import java.lang.reflect.ParameterizedType
import java.lang.reflect.Type

class ApiResponseCallAdapterFactory private constructor(): CallAdapter.Factory() {
    override fun get(
        returnType: Type,
        annotations: Array<out Annotation?>,
        retrofit: Retrofit
    ): CallAdapter<*, *>? = when (getRawType(returnType)) {
        Call::class.java -> {
            val callType = getParameterUpperBound(0, returnType as ParameterizedType)
            if (getRawType(callType) != ApiResponse::class.java) {
                null
            } else {
                val resultType = getParameterUpperBound(0, callType as ParameterizedType)
                ApiResponseCallAdapter<Any>(resultType)
            }
        }

        else -> null
    }

    companion object {
        @JvmStatic
        fun create() = ApiResponseCallAdapterFactory()
    }
}