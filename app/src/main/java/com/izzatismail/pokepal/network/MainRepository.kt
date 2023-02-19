package com.izzatismail.pokepal.network

import com.izzatismail.pokepal.model.PokemonResponse
import com.izzatismail.pokepal.model.ResultWrapper
import com.izzatismail.pokepal.model.SinglePokemonResponse
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
}