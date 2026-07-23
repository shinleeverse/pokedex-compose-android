package com.shinleeverse.pokedex.core.database.entity.mapper

interface EntityMapper<Domain, Entity> {
    fun toEntity(domain: Domain): Entity
    fun toDomain(entity: Entity): Domain
}