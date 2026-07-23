package com.shinleeverse.pokedex.core.common

import javax.inject.Qualifier

@Qualifier
@Retention(AnnotationRetention.RUNTIME)
annotation class Dispatcher(val pokedexAppDispatchers: PokedexAppDispatchers)

enum class PokedexAppDispatchers {
    IO
}