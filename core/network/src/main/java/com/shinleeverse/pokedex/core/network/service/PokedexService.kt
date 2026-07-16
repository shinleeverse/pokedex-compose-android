package com.shinleeverse.pokedex.core.network.service

import com.shinleeverse.pokedex.core.model.PokemonInfo
import com.shinleeverse.pokedex.core.network.model.PokemonResponse
import com.shinleeverse.pokedex.core.network.ApiResponse
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface PokedexService {

    @GET("pokemon")
    suspend fun fetchPokemonList(
        @Query("limit") limit : Int = 20,
        @Query("offset") offset : Int = 0,
    ) : ApiResponse<PokemonResponse>

    @GET("pokemon/{name}")
    suspend fun fetchPokemonInfo(
        @Path("name") name: String
    ) : ApiResponse<PokemonInfo>
}