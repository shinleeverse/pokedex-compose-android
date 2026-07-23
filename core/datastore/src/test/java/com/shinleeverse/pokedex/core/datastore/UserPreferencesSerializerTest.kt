package com.shinleeverse.pokedex.core.datastore

import androidx.datastore.core.CorruptionException
import junit.framework.TestCase.assertEquals
import kotlinx.coroutines.test.runTest
import org.junit.Test
import java.io.ByteArrayInputStream
import java.io.ByteArrayOutputStream

class UserPreferencesSerializerTest {

    @Test
    fun defaultUserPreferences_isEmpty() {
        val serializer = UserPreferencesSerializer()
        assertEquals(
            userPreferences { },
            serializer.defaultValue
        )
    }

    @Test
    fun writingAndReadingUserPreferences_outputsCorrectValue() = runTest {
        val serializer = UserPreferencesSerializer()

        val expected = userPreferences {
            uiThemeConfig = UiThemeConfig.UI_THEME_CONFIG_SYSTEM_DEFAULT
        }

        val outputStream = ByteArrayOutputStream()
        expected.writeTo(outputStream)
        val inputStream = ByteArrayInputStream(outputStream.toByteArray())
        val actual = serializer.readFrom(input = inputStream)

        assertEquals(expected, actual)
    }

    @Test(expected = CorruptionException::class)
    fun readingInvalidUserPreferences_throwsCorruptionException() = runTest {
        val serializer = UserPreferencesSerializer()
        serializer.readFrom(ByteArrayInputStream(byteArrayOf(0)))
    }
}