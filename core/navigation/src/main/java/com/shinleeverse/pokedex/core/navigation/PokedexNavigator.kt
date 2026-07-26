package com.shinleeverse.pokedex.core.navigation

import androidx.navigation3.runtime.NavBackStack
import androidx.navigation3.runtime.NavKey

interface PokedexNavigator {

    fun navigate(screen: PokedexScreen)

    fun navigateUp() : Boolean
}

class PokedexNavigatorImpl(
    private val backStack: NavBackStack<NavKey>
) : PokedexNavigator {
    override fun navigate(screen: PokedexScreen) {
        backStack.add(screen)
    }

    override fun navigateUp(): Boolean {
        return if(backStack.size > 1){
            backStack.removeLastOrNull() != null
        } else {
            false
        }
    }
}