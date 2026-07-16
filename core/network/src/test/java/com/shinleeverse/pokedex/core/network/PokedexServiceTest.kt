package com.shinleeverse.pokedex.core.network

import com.shinleeverse.pokedex.core.network.service.PokedexService
import kotlinx.coroutines.test.runTest
import okio.IOException
import org.hamcrest.CoreMatchers.`is`
import org.hamcrest.MatcherAssert.assertThat
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4

@RunWith(JUnit4::class)
class PokedexServiceTest : ApiServiceTest<PokedexService>(){

    private lateinit var service: PokedexService

    @Before
    fun initService(){
        service = createService(PokedexService::class.java)
    }

    @Throws(IOException::class)
    @Test
    fun fetchPokemonListFromNetworkTest() = runTest {
        enqueueResponse("/PokemonResponse.json")
        val response = service.fetchPokemonList()
        if (response is ApiResponse.Exception) {
            throw response.exception
        }
        val responseBody = requireNotNull((response as ApiResponse.Success).data)

        assertThat(responseBody.count, `is`(964))
        assertThat(responseBody.results[0].name, `is`("Bulbasaur"))
        assertThat(responseBody.results[0].url, `is`("https://pokeapi.co/api/v2/pokemon/1/"))
    }

    @Throws(IOException::class)
    @Test
    fun fetchPokemonInfoFromNetworkTest() = runTest{
        enqueueResponse("/Bulbasaur.json")
        val response = service.fetchPokemonInfo("bulbasaur")
        if (response is ApiResponse.Exception) {
            throw response.exception
        }
        val responseBody = requireNotNull((response as ApiResponse.Success).data)

        assertThat(responseBody.id, `is`(1))
        assertThat(responseBody.name, `is`("bulbasaur"))
        assertThat(responseBody.height, `is`(7))
        assertThat(responseBody.weight, `is`(69))
        assertThat(responseBody.exp, `is`(64))
    }
}