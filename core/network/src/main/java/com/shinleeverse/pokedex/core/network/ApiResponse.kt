package com.shinleeverse.pokedex.core.network

/**
 * A sealed interface representing the result of an API request.
 */
sealed interface ApiResponse<out T> {
    /**
     * Represents a successful network request with a 2xx status code and valid body.
     */
    data class Success<out T>(val data: T) : ApiResponse<T>

    /**
     * Represents an API-level error response with a non-2xx status code.
     */
    data class Failure(val code: Int, val message: String?) : ApiResponse<Nothing>

    /**
     * Represents a local exception (e.g. timeout, DNS resolution failure, JSON parsing exception).
     */
    data class Exception(val exception: Throwable) : ApiResponse<Nothing>
}