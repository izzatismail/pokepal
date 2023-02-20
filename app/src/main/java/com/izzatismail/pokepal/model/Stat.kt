package com.izzatismail.pokepal.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Stat(
    val name: String,
    val url: String
) : Parcelable {
    fun getFormattedStatName(): String {
        return name.capitalize()
    }
}