package com.izzatismail.pokepal.network

import com.izzatismail.pokepal.model.PokemonResponse
import com.izzatismail.pokepal.model.ResultWrapper
import com.izzatismail.pokepal.model.SinglePokemonResponse
import com.izzatismail.pokepal.network.api.PokePalAPI
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers

class ApiHelper(private val pokePalAPI: PokePalAPI): ApiReference {
    private val dispatcher: CoroutineDispatcher = Dispatchers.IO
    override suspend fun getPokemons(limit: Int?, offset: Int?): ResultWrapper<PokemonResponse> {
        return makeSafeApiCall(dispatcher = dispatcher) { pokePalAPI.getPokemons(limit = limit, offset = offset) }
    }

    override suspend fun getSinglePokemon(id: Int): ResultWrapper<SinglePokemonResponse> {
        return makeSafeApiCall(dispatcher = dispatcher) { pokePalAPI.getSinglePokemon(id = id) }
    }
}