package com.izzatismail.pokepal.views.main.viewmodel

import android.content.Context
import androidx.lifecycle.viewModelScope
import com.google.gson.Gson
import com.izzatismail.pokepal.base.BaseViewModel
import com.izzatismail.pokepal.model.PokemonResult
import com.izzatismail.pokepal.model.response.ResultWrapper
import com.izzatismail.pokepal.model.response.SinglePokemonResponse
import com.izzatismail.pokepal.model.sqlite.FavouritePokemonJson
import com.izzatismail.pokepal.network.MainRepository
import com.izzatismail.pokepal.utils.Constants
import com.izzatismail.pokepal.utils.Utils.extractId
import com.izzatismail.pokepal.views.main.uistate.MainUIState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class MainViewModel(private val context: Context, private val repository: MainRepository) : BaseViewModel() {
    private val mainUIState = MutableStateFlow<MainUIState>(MainUIState.Empty)
    val uiState: StateFlow<MainUIState> = mainUIState

    fun getPokemons(limit: Int?, offset: Int?) = viewModelScope.launch {
        mainUIState.value = MainUIState.IsLoading(isLoading = true)
        repository.getPokemons(limit = limit, offset = offset).collect {
            mainUIState.value = MainUIState.IsLoading(isLoading = false)
            when (it) {
                is ResultWrapper.NetworkError -> {
                    mainUIState.value = MainUIState.ShowError(message = Constants.GENERIC_ERROR_MESSAGE)
                }
                is ResultWrapper.GenericError -> {
                    mainUIState.value = MainUIState.ShowError(message = it.apiErrorResponse?.status_message ?: Constants.GENERIC_ERROR_MESSAGE)
                }
                is ResultWrapper.Success -> {
                    mainUIState.value = MainUIState.SuccessResponse(response = it.value)
                }
            }
        }
    }

    fun getSinglePokemonData(pokemonResult: PokemonResult) = viewModelScope.launch {
        mainUIState.value = MainUIState.IsLoading(isLoading = true)
        repository.getSinglePokemon(id = pokemonResult.url.extractId()).collect {
            mainUIState.value = MainUIState.IsLoading(isLoading = false)
            when (it) {
                is ResultWrapper.NetworkError -> {
                    mainUIState.value = MainUIState.ShowError(message = Constants.GENERIC_ERROR_MESSAGE)
                }
                is ResultWrapper.GenericError -> {
                    mainUIState.value = MainUIState.ShowError(message = it.apiErrorResponse?.status_message ?: Constants.GENERIC_ERROR_MESSAGE)
                }
                is ResultWrapper.Success -> {
                    mainUIState.value = MainUIState.SinglePokemonSuccessResponse(response = it.value, selectedPokemon = pokemonResult)
                }
            }
        }
    }

    fun addPokemonToFavourite(pokemonResult: PokemonResult, singlePokemonResponse: SinglePokemonResponse) {
        val pokemonJson = Gson().toJson(pokemonResult)
        val pokemonDetailsJson = Gson().toJson(singlePokemonResponse)

        val favouritePokemonJson = FavouritePokemonJson(uid = System.currentTimeMillis().toInt(), favouritePokemonJson = pokemonJson, pokemonDetailsJson = pokemonDetailsJson)
        repository.addFavouritePokemon(context = context, favouritePokemonJson = favouritePokemonJson)
    }

}