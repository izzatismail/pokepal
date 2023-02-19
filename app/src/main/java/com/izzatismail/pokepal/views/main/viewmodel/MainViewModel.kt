package com.izzatismail.pokepal.views.main.viewmodel

import androidx.lifecycle.viewModelScope
import com.izzatismail.pokepal.base.BaseViewModel
import com.izzatismail.pokepal.model.ResultWrapper
import com.izzatismail.pokepal.network.MainRepository
import com.izzatismail.pokepal.utils.Constants
import com.izzatismail.pokepal.views.main.uistate.MainUIState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class MainViewModel(private val repository: MainRepository) : BaseViewModel() {
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

}