package com.izzatismail.pokepal.network.sqlite

import androidx.room.Database
import androidx.room.RoomDatabase
import com.izzatismail.pokepal.model.sqlite.FavouritePokemonJson

@Database(entities = [FavouritePokemonJson::class], version = 2)
abstract class FavouritePokemonDatabase : RoomDatabase() {
    abstract fun favouritesDAO(): FavouritesDAO
}