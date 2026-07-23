package com.shinleeverse.pokedex.core.common.di

import com.shinleeverse.pokedex.core.common.Dispatcher
import com.shinleeverse.pokedex.core.common.PokedexAppDispatchers
import com.shinleeverse.pokedex.core.common.PokedexAppScope
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.SupervisorJob
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
internal object CoroutineScopesModule {

    @Provides
    @Singleton
    @PokedexAppScope
    fun providesCoroutineScope(
        @Dispatcher(PokedexAppDispatchers.IO) dispatcher : CoroutineDispatcher
    ) : CoroutineScope = CoroutineScope(SupervisorJob() + dispatcher)
}