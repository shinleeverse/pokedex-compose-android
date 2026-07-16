package com.shinleeverse.pokedex.core.network

import org.hamcrest.CoreMatchers.`is`
import org.hamcrest.MatcherAssert.assertThat
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import retrofit2.Response

@RunWith(JUnit4::class)
class ApiResponseTest {

    @Test
    fun exception() {
        val exception = Exception("Foo")
        val apiResponse = ApiResponse.Exception(exception)
        assertThat(apiResponse.exception.message, `is`("Foo"))
    }

    @Test
    fun success() {
        val apiResponse = Response.success("foo").toApiResponse()
        if(apiResponse is ApiResponse.Success){
            assertThat(apiResponse.data, `is`("foo"))
        }
    }

}