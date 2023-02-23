package com.izzatismail.pokepal.network.sqlite

import android.content.Context
import androidx.room.Room
import com.izzatismail.pokepal.utils.Constants

class RoomService {
    companion object {
        fun provideRoomDB(applicationContext: Context) = Room.databaseBuilder(
        applicationContext,
        FavouritePokemonDatabase::class.java, Constants.SQLITE_DB_NAME
        )
            .fallbackToDestructiveMigration()
            .build()
    }
}