package com.izzatismail.pokepal.views.main.adapters

import com.izzatismail.pokepal.model.PokemonResult

interface PokemonListListener {
    fun onClick(pokemonResult: PokemonResult)
}