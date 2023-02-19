package com.izzatismail.pokepal.views.main.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.izzatismail.pokepal.databinding.ViewListItemPokemonBinding
import com.izzatismail.pokepal.model.PokemonResult
import com.izzatismail.pokepal.utils.Utils.extractId
import java.util.ArrayList

class PokemonListAdapter(var context: Context, var pokemonList: ArrayList<PokemonResult> = arrayListOf(), var listener: PokemonListListener?):  RecyclerView.Adapter<PokemonListAdapter.PokemonListViewHolder>(){

    inner class PokemonListViewHolder(var binding: ViewListItemPokemonBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(pokemon: PokemonResult) {
            binding.pokemon = pokemon
            binding.root.setOnClickListener {
                    listener?.onClick(id = pokemon.url.extractId())
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PokemonListViewHolder {
        val binding = ViewListItemPokemonBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return PokemonListViewHolder(binding)
    }

    override fun onBindViewHolder(holder: PokemonListViewHolder, position: Int) {
        holder.bind(pokemon = pokemonList[position])
    }

    override fun getItemCount(): Int {
        return pokemonList.size
    }

    fun updateData(newList: ArrayList<PokemonResult>) {
        pokemonList = newList
        notifyDataSetChanged()
    }

    fun addData(newList: ArrayList<PokemonResult>) {
        pokemonList.addAll(newList)
        notifyDataSetChanged()
    }
}