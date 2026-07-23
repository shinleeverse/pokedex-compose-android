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
class PokemonDaoTest : LocalDatabase() {
    private lateinit var  pokemonDao : PokemonDao

    @Before
    fun init() {
        pokemonDao = db.pokemonDao()
    }

    @Test
    fun insertAndLoadPokemonListTest() = runBlocking {
        val mockDataList = MockUtil.mockPokemonList().toEntity()
        pokemonDao.insertPokemonList(mockDataList)

        val loadFromDB = pokemonDao.getPokemonList(page_ = 0)
        assertThat(loadFromDB.toString(), `is`(mockDataList.toString()))

        val mockData = listOf(MockUtil.mockPokemon()).toEntity()[0]
        assertThat(loadFromDB[0].toString(), `is`(mockData.toString()))
    }
}