package com.shinleeverse.pokedex.core.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import kotlin.random.Random

@Serializable
data class PokemonInfo(
    @SerialName(value = "id") val id: Int,
    @SerialName(value = "name") val name: String,
    @SerialName(value = "height") val height: Int,
    @SerialName(value = "weight") val weight: Int,
    @SerialName(value = "types") val types: List<TypeResponse>,
    @SerialName(value = "stats") val stats: List<StatResponse>,
    @SerialName(value = "base_experience") val exp: Int = Random.nextInt(MAX_EXP)
) {
    val hp: Int by lazy {
        stats.firstOrNull { it.stat.name == "hp" }?.baseStat ?: Random.nextInt(MAX_HP)
    }
    val attack: Int by lazy {
        stats.firstOrNull { it.stat.name == "attack" }?.baseStat ?: Random.nextInt(MAX_ATTACK)
    }
    val defense: Int by lazy {
        stats.firstOrNull { it.stat.name == "defense" }?.baseStat ?: Random.nextInt(MAX_DEFENSE)
    }
    val speed: Int by lazy {
        stats.firstOrNull { it.stat.name == "speed" }?.baseStat ?: Random.nextInt(MAX_SPEED)
    }

    fun getIdString(): String = "#%03d".format(id)
    fun getWeightString(): String = "%.1f KG".format(weight.toFloat() / 10)
    fun getHeightString(): String = "%.1f M".format(height.toFloat() / 10)
    fun getHpString(): String = " $hp/$MAX_HP"
    fun getAttackString() : String = " $attack/$MAX_ATTACK"
    fun getDefenseString() : String = " $defense/$MAX_DEFENSE"
    fun getSpeedString() : String = " $speed/$MAX_SPEED"
    fun getExpString() : String = " $exp/$MAX_EXP"

    @Serializable
    data class TypeResponse(
        @SerialName(value = "slot") val slot: Int,
        @SerialName(value = "type") val type: Type
    )

    @Serializable
    data class Type(
        @SerialName(value = "name") val name: String
    )

    @Serializable
    data class StatResponse(
        @SerialName(value = "base_stat") val baseStat: Int,
        @SerialName(value = "effort") val effort: Int,
        @SerialName(value = "stat") val stat: Stat
    )

    @Serializable
    data class Stat(
        @SerialName(value = "name") val name: String
    )

    companion object {
        const val MAX_HP = 300
        const val MAX_ATTACK = 300
        const val MAX_DEFENSE = 300
        const val MAX_SPEED = 300
        const val MAX_EXP = 1000
    }

}
