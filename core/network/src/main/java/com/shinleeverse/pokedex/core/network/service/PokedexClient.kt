package com.shinleeverse.pokedex.core.network.service

import com.shinleeverse.pokedex.core.model.PokemonInfo
import com.shinleeverse.pokedex.core.network.model.PokemonResponse
import com.shinleeverse.pokedex.core.network.ApiResponse
import javax.inject.Inject

class PokedexClient @Inject constructor(
    private val pokedexService: PokedexService
) {
    suspend fun fetchPokemonList(page: Int): ApiResponse<PokemonResponse> {
        return pokedexService.fetchPokemonList(
            limit = PAGING_SIZE,
            offset = page * PAGING_SIZE
        )
    }

    suspend fun fetchPokemonInfo(name: String): ApiResponse<PokemonInfo> {
        return pokedexService.fetchPokemonInfo(name = name)
    }

    companion object {
        private const val PAGING_SIZE = 20
    }
}