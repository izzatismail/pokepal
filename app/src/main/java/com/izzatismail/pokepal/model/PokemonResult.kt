package com.izzatismail.pokepal.model

import android.os.Parcelable
import com.izzatismail.pokepal.utils.Utils.getPicUrl
import kotlinx.parcelize.Parcelize

@Parcelize
data class PokemonResult(
    val name: String,
    val url: String
) : Parcelable {
    fun getPicUrl(): String {
        return url.getPicUrl()
    }
}