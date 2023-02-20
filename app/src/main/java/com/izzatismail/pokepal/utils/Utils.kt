package com.izzatismail.pokepal.utils

import android.content.Context
import android.widget.Toast

object Utils {
    fun showToastMessage(context: Context, message: String) {
        Toast.makeText(context, message, Toast.LENGTH_LONG).show()
    }

    fun String.extractId() = this.substringAfter("pokemon").replace("/", "").toInt()

    fun String.getPicUrl(): String {
        val id = this.extractId()
        return "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/${id}.png"
    }
}