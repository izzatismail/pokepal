package com.izzatismail.pokepal.network.sqlite

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.izzatismail.pokepal.model.sqlite.FavouritePokemonJson

@Dao
interface FavouritesDAO {
    @Query("SELECT * FROM favourites")
    fun getAllFavourites(): List<FavouritePokemonJson>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(favouritePokemon: FavouritePokemonJson)
}