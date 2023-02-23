package com.izzatismail.pokepal.views.favourites.uistate

import com.izzatismail.pokepal.model.PokemonResult
import com.izzatismail.pokepal.model.response.SinglePokemonResponse

sealed class FavouritesUIState {
    object Empty: FavouritesUIState()
    data class ShowFavouritePokemonList(val list: ArrayList<PokemonResult>) : FavouritesUIState()
    data class ShowFavouritePokemonDetail(val pokemonDetail: SinglePokemonResponse) : FavouritesUIState()
    data class ShowError(val message: String): FavouritesUIState()
}