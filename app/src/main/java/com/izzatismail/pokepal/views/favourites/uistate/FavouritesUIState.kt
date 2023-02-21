package com.izzatismail.pokepal.views.favourites.uistate

sealed class FavouritesUIState {
    object Empty: FavouritesUIState()
    data class IsLoading(val isLoading: Boolean): FavouritesUIState()
    data class ShowError(val message: String): FavouritesUIState()
}