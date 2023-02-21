package com.izzatismail.pokepal.views.favourites.viewmodel

import com.izzatismail.pokepal.base.BaseViewModel
import com.izzatismail.pokepal.model.PokemonResult
import com.izzatismail.pokepal.network.MainRepository
import com.izzatismail.pokepal.views.favourites.uistate.FavouritesUIState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class FavouritesViewModel(private val repository: MainRepository): BaseViewModel() {
    private val mainUIState = MutableStateFlow<FavouritesUIState>(FavouritesUIState.Empty)
    val uiState: StateFlow<FavouritesUIState> = mainUIState

    fun getFavouriteList() {

    }

    fun getSinglePokemonData(pokemonResult: PokemonResult) {

    }
}