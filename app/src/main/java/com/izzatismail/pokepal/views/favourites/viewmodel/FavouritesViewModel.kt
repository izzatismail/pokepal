package com.izzatismail.pokepal.views.favourites.viewmodel

import android.content.Context
import androidx.lifecycle.viewModelScope
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.izzatismail.pokepal.base.BaseViewModel
import com.izzatismail.pokepal.model.PokemonResult
import com.izzatismail.pokepal.model.response.SinglePokemonResponse
import com.izzatismail.pokepal.network.MainRepository
import com.izzatismail.pokepal.views.favourites.uistate.FavouritesUIState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class FavouritesViewModel(private val context: Context, private val repository: MainRepository): BaseViewModel() {
    private val favouriteUIState = MutableStateFlow<FavouritesUIState>(FavouritesUIState.Empty)
    val uiState: StateFlow<FavouritesUIState> = favouriteUIState

    fun getFavouriteList() = viewModelScope.launch {
        repository.getFavouritePokemonList(context = context).collect { favouritePokemonList ->

            if (favouritePokemonList.isNotEmpty()) {
                val favouriteList = ArrayList<PokemonResult>()
                favouritePokemonList.forEach { pokemonResult ->
                    val pokemon = Gson().fromJson(pokemonResult.favouritePokemonJson, PokemonResult::class.java)
                    favouriteList.add(pokemon)
                }
                favouriteUIState.value = FavouritesUIState.ShowFavouritePokemonList(list = favouriteList)
            }
        }
    }

    fun getSinglePokemonData(pokemonResult: PokemonResult) {
        //TODO implement
        //FavouritesUIState.ShowFavouritePokemonDetail(pokemonDetail = )
    }
}