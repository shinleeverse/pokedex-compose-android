package com.shinleeverse.pokedex.core.network

import retrofit2.Response

fun <T> Response<T>.toApiResponse() : ApiResponse<T> {
    val body = body()
    return if(isSuccessful){
        if(body != null){
            ApiResponse.Success(body)
        } else {
            ApiResponse.Failure(code(), "Response body was empty")
        }
    } else {
        ApiResponse.Failure(code(), errorBody()?.string())
    }
}