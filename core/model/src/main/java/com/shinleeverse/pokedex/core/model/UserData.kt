package com.shinleeverse.pokedex.core.model

data class UserData(
    val uiTheme : UiTheme
)

enum class UiTheme {
    SYSTEM_DEFAULT,
    DARK,
    LIGHT
}
