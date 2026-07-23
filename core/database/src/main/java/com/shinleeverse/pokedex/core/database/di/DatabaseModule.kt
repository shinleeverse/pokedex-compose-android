package com.shinleeverse.pokedex.core.database.di

import android.app.Application
import androidx.room.Room
import com.shinleeverse.pokedex.core.database.PokedexDatabase
import com.shinleeverse.pokedex.core.database.PokemonDao
import com.shinleeverse.pokedex.core.database.PokemonInfoDao
import com.shinleeverse.pokedex.core.database.StatMetaDataConverter
import com.shinleeverse.pokedex.core.database.TypeMetaDataConverter
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.serialization.json.Json
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
internal object DatabaseModule {

    @Provides
    @Singleton
    fun providesAppDatabase(
        application: Application,
        typeMetaDataConverter: TypeMetaDataConverter,
        statMetaDataConverter: StatMetaDataConverter
    ): PokedexDatabase {
        return Room.databaseBuilder(
            application,
            PokedexDatabase::class.java,
            PokedexDatabase.DB_NAME
        )
            .addTypeConverter(typeMetaDataConverter)
            .addTypeConverter(statMetaDataConverter)
            .build()
    }

    @Provides
    @Singleton
    fun providesPokemonDao(appDatabase : PokedexDatabase) : PokemonDao {
        return appDatabase.pokemonDao()
    }

    @Provides
    @Singleton
    fun providesPokemonInfoDao(appDatabase : PokedexDatabase) : PokemonInfoDao {
        return appDatabase.pokemonInfoDao()
    }

    @Provides
    @Singleton
    fun providesTypeMetaDataConverter(json : Json) : TypeMetaDataConverter {
        return TypeMetaDataConverter(json)
    }
}