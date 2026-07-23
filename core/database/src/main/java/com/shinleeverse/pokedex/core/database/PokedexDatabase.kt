package com.shinleeverse.pokedex.core.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.shinleeverse.pokedex.core.database.entity.PokemonEntity
import com.shinleeverse.pokedex.core.database.entity.PokemonInfoEntity

@Database(
    entities = [
        PokemonEntity::class,
        PokemonInfoEntity::class,
    ],
    version = 1,
    exportSchema = true
)
@TypeConverters(
    value = [
        TypeMetaDataConverter::class,
        StatMetaDataConverter::class
    ]
)
abstract class PokedexDatabase : RoomDatabase() {
    abstract fun pokemonDao(): PokemonDao
    abstract fun pokemonInfoDao(): PokemonInfoDao

    companion object {
        const val DB_NAME = "pokedex_db"
    }
}