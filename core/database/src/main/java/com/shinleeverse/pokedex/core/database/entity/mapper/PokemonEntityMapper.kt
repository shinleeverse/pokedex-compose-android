package com.shinleeverse.pokedex.core.database.entity.mapper

import com.shinleeverse.pokedex.core.database.entity.PokemonEntity
import com.shinleeverse.pokedex.core.model.Pokemon

object PokemonEntityMapper : EntityMapper<List<Pokemon>, List<PokemonEntity>> {

    override fun toEntity(domain: List<Pokemon>): List<PokemonEntity> {
        return domain.map { pokemon ->
            PokemonEntity(
                name = pokemon.name,
                url = pokemon.url
            )
        }
    }

    override fun toDomain(entity: List<PokemonEntity>): List<Pokemon> {
        return entity.map { pokemonEntity ->
            Pokemon(
                nameField = pokemonEntity.name,
                url = pokemonEntity.url
            )
        }
    }
}

fun List<Pokemon>.toEntity(): List<PokemonEntity> {
    return PokemonEntityMapper.toEntity(this)
}

fun List<PokemonEntity>.toDomain(): List<Pokemon> {
    return PokemonEntityMapper.toDomain(this)
}
