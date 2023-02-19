package com.izzatismail.pokepal.model.response

import android.os.Parcelable
import com.izzatismail.pokepal.model.Sprites
import com.izzatismail.pokepal.model.Stats
import kotlinx.parcelize.Parcelize

@Parcelize
data class SinglePokemonResponse(
    val sprites: Sprites,
    val stats: List<Stats>,
    val height: Int,
    val weight: Int
) : Parcelable