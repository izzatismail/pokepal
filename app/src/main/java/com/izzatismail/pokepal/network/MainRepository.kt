package com.izzatismail.pokepal.network

import android.content.Context
import com.izzatismail.pokepal.model.response.PokemonResponse
import com.izzatismail.pokepal.model.response.ResultWrapper
import com.izzatismail.pokepal.model.response.SinglePokemonResponse
import com.izzatismail.pokepal.model.sqlite.FavouritePokemonJson
import com.izzatismail.pokepal.network.sqlite.RoomService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn

class MainRepository(private var apiHelper: ApiHelper) {

    suspend fun getPokemons(limit: Int?, offset: Int?): Flow<ResultWrapper<PokemonResponse>> {
        return flow {
            emit(apiHelper.getPokemons(limit = limit, offset = offset))
        }.flowOn(Dispatchers.IO)
    }

    suspend fun getSinglePokemon(id: Int): Flow<ResultWrapper<SinglePokemonResponse>> {
        return flow {
            emit(apiHelper.getSinglePokemon(id = id))
        }.flowOn(Dispatchers.IO)
    }

    fun getFavouritePokemonList(context: Context): Flow<List<FavouritePokemonJson>>{
        val db = RoomService.provideRoomDB(applicationContext = context.applicationContext)
        return flow {
            emit(db.favouritesDAO().getAllFavourites())
        }.flowOn(Dispatchers.IO)
    }

    fun addFavouritePokemon(context: Context, favouritePokemonJson: FavouritePokemonJson) {
        val db = RoomService.provideRoomDB(applicationContext = context.applicationContext)
        flow {
            emit(db.favouritesDAO().insert(favouritePokemon = favouritePokemonJson))
        }
    }
}