package com.izzatismail.pokepal.model.response

import android.os.Parcelable
import com.izzatismail.pokepal.model.PokemonResult
import kotlinx.parcelize.Parcelize

@Parcelize
data class PokemonResponse(
    val count: Int,
    val next: String?,
    val previous: String?,
    val results: ArrayList<PokemonResult>
) : Parcelable