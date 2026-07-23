package com.shinleeverse.pokedex.core.database

import com.shinleeverse.pokedex.core.database.entity.mapper.toEntity
import kotlinx.coroutines.runBlocking
import org.hamcrest.CoreMatchers.`is`
import org.hamcrest.MatcherAssert.assertThat
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner
import org.robolectric.annotation.Config

@RunWith(RobolectricTestRunner::class)
@Config(sdk = [23])
class PokemonInfoDaoTest : LocalDatabase() {

    private lateinit var pokemonInfoDao: PokemonInfoDao

    @Before
    fun init() {
        pokemonInfoDao = db.pokemonInfoDao()
    }

    @Test
    fun insertAndLoadPokemonInfoTest() = runBlocking{
        val mockPokemonInfo = MockUtil.mockPokemonInfo()
        pokemonInfoDao.insertPokemonInfo(mockPokemonInfo.toEntity())

        val loadFromDbById = pokemonInfoDao.getPokemonInfoById(mockPokemonInfo.id)
        assertThat(loadFromDbById.toString(), `is`(mockPokemonInfo.toEntity().toString()))

        val loadFromDbByName = pokemonInfoDao.getPokemonInfoByName(mockPokemonInfo.name)
        assertThat(loadFromDbByName.toString(), `is`(mockPokemonInfo.toEntity().toString()))
    }
}