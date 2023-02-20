package com.izzatismail.pokepal.views.main.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.izzatismail.pokepal.databinding.ViewListItemPokemonBinding
import com.izzatismail.pokepal.model.PokemonResult
import java.util.ArrayList

class PokemonListAdapter(var pokemonList: ArrayList<PokemonResult> = arrayListOf(), var listener: PokemonListListener?):  RecyclerView.Adapter<PokemonListAdapter.PokemonListViewHolder>(){

    inner class PokemonListViewHolder(var binding: ViewListItemPokemonBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(pokemon: PokemonResult) {
            binding.pokemon = pokemon
            binding.root.setOnClickListener {
                    listener?.onClick(pokemonResult = pokemon)
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