package com.shinleeverse.pokedex.core.navigation

import com.shinleeverse.pokedex.core.model.Pokemon
import kotlinx.serialization.Serializable

sealed interface PokedexScreen {

    @Serializable
    data object List: PokedexScreen

    @Serializable
    data class Details(val pokemon: Pokemon) : PokedexScreen

    @Serializable
    data object Settings : PokedexScreen

}