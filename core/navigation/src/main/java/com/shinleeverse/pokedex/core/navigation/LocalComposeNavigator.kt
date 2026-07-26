package com.shinleeverse.pokedex.core.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.ProvidableCompositionLocal
import androidx.compose.runtime.ReadOnlyComposable
import androidx.compose.runtime.compositionLocalOf

public val LocalComposeNavigator: ProvidableCompositionLocal<PokedexNavigator> =
    compositionLocalOf {
        error(
            "No PokedexNavigator provided! " +
                    "Make sure to wrap all usages of Pokedex components in PokedexTheme."
        )
    }

/**
 * Retrivves the current [PokedexNavigator] at the call  site's position in the hierarchy.
 */
public val currentComposeNavigator : PokedexNavigator
    @Composable
    @ReadOnlyComposable
    get() = LocalComposeNavigator.current