package com.shinleeverse.pokedex.core.navigation

import androidx.navigation3.runtime.NavKey
import com.shinleeverse.pokedex.core.model.Pokemon
import kotlinx.serialization.Serializable

sealed interface PokedexScreen : NavKey {

    @Serializable
    data object List: PokedexScreen

    @Serializable
    data class Details(val pokemon: Pokemon) : PokedexScreen

    @Serializable
    data object Settings : PokedexScreen

}