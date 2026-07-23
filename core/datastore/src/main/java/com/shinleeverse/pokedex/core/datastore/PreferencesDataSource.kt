package com.shinleeverse.pokedex.core.datastore

import androidx.datastore.core.DataStore
import com.shinleeverse.pokedex.core.model.UiTheme
import com.shinleeverse.pokedex.core.model.UserData
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class PreferencesDataSource @Inject constructor(
    private val userPreferences : DataStore<UserPreferences>
) {
    val userData = userPreferences.data.map {
        UserData(uiTheme = it.uiThemeConfig.toUiTheme())
    }

    suspend fun setUiTheme(uiTheme: UiTheme) {
        userPreferences.updateData {
            it.copy {
                uiThemeConfig = uiTheme.toUiThemeConfig()
            }
        }
    }
}

fun UiThemeConfig.toUiTheme() : UiTheme = when(this) {
    UiThemeConfig.UI_THEME_CONFIG_UNSPECIFIED -> UiTheme.SYSTEM_DEFAULT
    UiThemeConfig.UI_THEME_CONFIG_SYSTEM_DEFAULT -> UiTheme.SYSTEM_DEFAULT
    UiThemeConfig.UI_THEME_CONFIG_LIGHT -> UiTheme.LIGHT
    UiThemeConfig.UI_THEME_CONFIG_DARK -> UiTheme.DARK
    UiThemeConfig.UNRECOGNIZED -> UiTheme.SYSTEM_DEFAULT
}

fun UiTheme.toUiThemeConfig() : UiThemeConfig = when(this){
    UiTheme.SYSTEM_DEFAULT -> UiThemeConfig.UI_THEME_CONFIG_SYSTEM_DEFAULT
    UiTheme.DARK -> UiThemeConfig.UI_THEME_CONFIG_DARK
    UiTheme.LIGHT -> UiThemeConfig.UI_THEME_CONFIG_LIGHT
}