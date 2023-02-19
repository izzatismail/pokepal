package com.izzatismail.pokepal.network

import com.izzatismail.pokepal.model.PokemonResponse
import com.izzatismail.pokepal.model.ResultWrapper
import com.izzatismail.pokepal.model.SinglePokemonResponse

interface ApiReference {
    /**
     * GET movie list
     * @param sortOption
     * @param pageNum
     *
     */
    suspend fun getPokemons(limit: Int?, offset: Int?): ResultWrapper<PokemonResponse>

    /**
     * GET details of movie selected based on id
     * @param id
     *
     */
    suspend fun getSinglePokemon(id: Int): ResultWrapper<SinglePokemonResponse>
}