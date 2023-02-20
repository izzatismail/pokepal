package com.izzatismail.pokepal.views.main.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.izzatismail.pokepal.databinding.ViewListItemStatsBinding
import com.izzatismail.pokepal.model.Stats
import java.util.ArrayList

class PokemonStatsListAdapter(var statsList: ArrayList<Stats> = arrayListOf()):  RecyclerView.Adapter<PokemonStatsListAdapter.PokemonStatsListViewHolder>() {

    inner class PokemonStatsListViewHolder(var binding: ViewListItemStatsBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(stats: Stats) {
            binding.stats = stats
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PokemonStatsListViewHolder {
        val binding = ViewListItemStatsBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return PokemonStatsListViewHolder(binding)
    }

    override fun onBindViewHolder(holder: PokemonStatsListViewHolder, position: Int) {
        holder.bind(stats = statsList[position])
    }

    override fun getItemCount(): Int {
        return statsList.size
    }
}