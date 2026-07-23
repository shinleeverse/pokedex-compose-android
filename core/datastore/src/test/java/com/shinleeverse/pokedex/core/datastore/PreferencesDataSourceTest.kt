package com.shinleeverse.pokedex.core.datastore

import com.shinleeverse.pokedex.core.model.UiTheme
import junit.framework.TestCase.assertEquals
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.test.TestScope
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Test

class PreferencesDataSourceTest {

    private val testScope = TestScope(UnconfinedTestDispatcher())

    private lateinit var subject: PreferencesDataSource

    @Before
    fun setup() {
        subject = PreferencesDataSource(
            userPreferences = InMemoryDataStore(UserPreferences.getDefaultInstance())
        )
    }

    @Test
    fun shouldThemeIsSystemDefault() = testScope.runTest {
        assertEquals(UiTheme.SYSTEM_DEFAULT, subject.userData.first().uiTheme)
    }

    @Test
    fun userShouldThemeIsDarkWhenSet() = testScope.runTest {
        subject.setUiTheme(uiTheme = UiTheme.DARK)
        assertEquals(UiTheme.DARK, subject.userData.first().uiTheme)
    }

}