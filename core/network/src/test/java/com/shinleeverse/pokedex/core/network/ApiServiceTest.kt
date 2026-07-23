package com.shinleeverse.pokedex.core.network

import com.shinleeverse.pokedex.core.network.adapters.ApiResponseCallAdapterFactory
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import org.junit.After
import org.junit.Before
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import retrofit2.Retrofit
import retrofit2.converter.kotlinx.serialization.asConverterFactory

@RunWith(JUnit4::class)
abstract class ApiServiceTest<T> {

    lateinit var mockWebServer: MockWebServer

    @Before
    fun mockServer() {
        mockWebServer = MockWebServer()
        mockWebServer.start()
    }

    @After
    fun stopServer() {
        mockWebServer.shutdown()
    }

    fun enqueueResponse(fileName: String) {
        enqueueResponse(fileName, emptyMap())
    }

    fun createService(clazz: Class<T>): T {
        val json = Json { ignoreUnknownKeys = true }
        return Retrofit.Builder()
            .baseUrl(mockWebServer.url("/"))
            .addConverterFactory(json.asConverterFactory("application/json".toMediaType()))
            .addCallAdapterFactory(ApiResponseCallAdapterFactory.create())
            .build()
            .create(clazz)
    }

    private fun enqueueResponse(fileName: String, headers: Map<String, String>) {
        val inputStream = javaClass.classLoader!!.getResourceAsStream("api-responses/$fileName")
            ?: throw IllegalArgumentException("Resource file not found: api-responses/$fileName")
        val source = inputStream.bufferedReader().use { it.readText() }
        val mockResponse = MockResponse().setBody(source)
        for ((key, value) in headers) {
            mockResponse.addHeader(key, value)
        }
        mockWebServer.enqueue(mockResponse)
    }

}