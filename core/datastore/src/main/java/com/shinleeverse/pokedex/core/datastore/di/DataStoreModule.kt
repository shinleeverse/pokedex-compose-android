package com.shinleeverse.pokedex.core.datastore.di

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.core.DataStoreFactory
import androidx.datastore.dataStoreFile
import com.shinleeverse.pokedex.core.common.Dispatcher
import com.shinleeverse.pokedex.core.common.PokedexAppDispatchers
import com.shinleeverse.pokedex.core.common.PokedexAppScope
import com.shinleeverse.pokedex.core.datastore.UserPreferences
import com.shinleeverse.pokedex.core.datastore.UserPreferencesSerializer
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.CoroutineScope
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DataStoreModule {

    @Provides
    @Singleton
    fun providesUserPreferencesDataStore(
        @ApplicationContext context: Context,
        @Dispatcher(PokedexAppDispatchers.IO) dispatcher: CoroutineDispatcher,
        @PokedexAppScope scope: CoroutineScope,
        userPreferencesSerializer: UserPreferencesSerializer
    ): DataStore<UserPreferences> = DataStoreFactory
        .create(
            serializer = userPreferencesSerializer,
            scope = CoroutineScope(scope.coroutineContext + dispatcher),
            produceFile = { context.dataStoreFile(fileName = "user_preferences.pb") }
        )
}