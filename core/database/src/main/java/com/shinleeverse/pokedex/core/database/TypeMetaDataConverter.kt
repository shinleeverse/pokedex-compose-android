package com.shinleeverse.pokedex.core.database

import androidx.room.ProvidedTypeConverter
import androidx.room.TypeConverter
import com.shinleeverse.pokedex.core.model.PokemonInfo
import kotlinx.serialization.json.Json
import javax.inject.Inject

@ProvidedTypeConverter
class TypeMetaDataConverter @Inject constructor(
    private val json : Json
) {
    @TypeConverter
    fun fromString(value : String) : List<PokemonInfo.TypeMetaData>{
        return json.decodeFromString(value)
    }

    @TypeConverter
    fun fromInfoType(type: List<PokemonInfo.TypeMetaData>?) : String {
        return json.encodeToString(type)
    }
}