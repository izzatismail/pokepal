package com.izzatismail.pokepal.model.sqlite

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.izzatismail.pokepal.model.PokemonResult

@Entity(tableName = "favourites")
data class FavouritePokemonJson(
    @PrimaryKey(autoGenerate = true)
    val uid: Int,

    @ColumnInfo(name = "favourite_pokemon")
    val favouritePokemonJson: String?,

    @ColumnInfo(name = "pokemon_details")
    val pokemonDetailsJson: String?
)