package com.izzatismail.pokepal.views.main.viewmodel

import com.izzatismail.pokepal.base.BaseViewModel
import com.izzatismail.pokepal.network.MainRepository
import com.izzatismail.pokepal.views.main.uistate.MainUIState
import kotlinx.coroutines.flow.MutableStateFlow

class MainViewModel(private val repository: MainRepository) : BaseViewModel() {
    val uiState = MutableStateFlow(MainUIState.IsLoading(true))


}