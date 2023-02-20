package com.izzatismail.pokepal.views.main.uistate

import com.izzatismail.pokepal.model.PokemonResult
import com.izzatismail.pokepal.model.response.PokemonResponse
import com.izzatismail.pokepal.model.response.SinglePokemonResponse

sealed class MainUIState {
    object Empty: MainUIState()
    data class IsLoading(val isLoading: Boolean): MainUIState()
    data class SuccessResponse(val response: PokemonResponse): MainUIState()
    data class SinglePokemonSuccessResponse(val response: SinglePokemonResponse, val selectedPokemon: PokemonResult): MainUIState()
    data class ShowError(val message: String): MainUIState()
}