package com.izzatismail.pokepal.views.main.uistate

import com.izzatismail.pokepal.model.PokemonResponse

sealed class MainUIState {
    object Empty: MainUIState()
    data class IsLoading(val isLoading: Boolean): MainUIState()
    data class SuccessResponse(val response: PokemonResponse): MainUIState()
    data class ShowError(val message: String): MainUIState()
}