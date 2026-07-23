package com.shinleeverse.pokedex.core.database.entity.mapper

import com.shinleeverse.pokedex.core.database.entity.PokemonInfoEntity
import com.shinleeverse.pokedex.core.model.PokemonInfo

object PokemonInfoEntityMapper : EntityMapper<PokemonInfo, PokemonInfoEntity> {
    override fun toEntity(domain: PokemonInfo): PokemonInfoEntity {
        return PokemonInfoEntity(
            id = domain.id,
            name = domain.name,
            height = domain.height,
            weight = domain.weight,
            experience = domain.experience,
            types = domain.types,
            stats = domain.stats,
            exp = domain.exp
        )
    }

    override fun toDomain(entity: PokemonInfoEntity): PokemonInfo {
        return PokemonInfo(
            id = entity.id,
            name = entity.name,
            height = entity.height,
            weight = entity.weight,
            experience = entity.experience,
            types = entity.types,
            stats = entity.stats,
            exp = entity.exp
        )
    }
}

fun PokemonInfo.toEntity() : PokemonInfoEntity {
    return PokemonInfoEntityMapper.toEntity(this)
}

fun PokemonInfoEntity.toDomain() : PokemonInfo {
    return PokemonInfoEntityMapper.toDomain(this)
}