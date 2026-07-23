package com.shinleeverse.pokedex.core.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.shinleeverse.pokedex.core.database.entity.PokemonInfoEntity

@Dao
interface PokemonInfoDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertPokemonInfo(pokemonInfo: PokemonInfoEntity)

    @Query("SELECT * FROM PokemonInfoEntity WHERE name = :name_")
    suspend fun getPokemonInfoByName(name_: String): PokemonInfoEntity?

    @Query("SELECT * FROM PokemonInfoEntity WHERE id = :id_")
    suspend fun getPokemonInfoById(id_: Int): PokemonInfoEntity?
}