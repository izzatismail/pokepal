package com.izzatismail.pokepal.views.favourites.view

import android.os.Bundle
import android.view.View
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.lifecycleScope
import com.izzatismail.pokepal.R
import com.izzatismail.pokepal.base.BaseActivity
import com.izzatismail.pokepal.databinding.ActivityFavouritesBinding
import com.izzatismail.pokepal.model.PokemonResult
import com.izzatismail.pokepal.model.response.SinglePokemonResponse
import com.izzatismail.pokepal.utils.Utils
import com.izzatismail.pokepal.utils.showDialogFragment
import com.izzatismail.pokepal.views.favourites.uistate.FavouritesUIState
import com.izzatismail.pokepal.views.favourites.viewmodel.FavouritesViewModel
import com.izzatismail.pokepal.views.main.adapters.PokemonListAdapter
import com.izzatismail.pokepal.views.main.adapters.PokemonListListener
import com.izzatismail.pokepal.views.main.view.PokemonDetailFragment
import org.koin.androidx.viewmodel.ext.android.getViewModel

class FavouritesActivity: BaseActivity() {
    private lateinit var mBinding: ActivityFavouritesBinding
    private lateinit var mViewModel: FavouritesViewModel

    private var mAdapter: PokemonListAdapter? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        mBinding.executePendingBindings()
        hideActionBar()
        initViewModel()
        mViewModel.getFavouriteList()
    }

    private fun initViewModel() {
        mViewModel = getViewModel()
        lifecycleScope.launchWhenStarted {
            mViewModel.uiState.collect { uiState ->
                when (uiState) {
                    is FavouritesUIState.IsLoading -> {
                        mBinding.pbProgressBar.visibility = if (uiState.isLoading) View.VISIBLE else View.GONE
                    }
//                    is FavouritesUIState.SuccessResponse -> {
//                        mAdapter = PokemonListAdapter(pokemonList = uiState.response.results, listener =  object:
//                            PokemonListListener {
//                            override fun onClick(pokemonResult: PokemonResult) {
//                                mViewModel.getSinglePokemonData(pokemonResult = pokemonResult)
//                            }
//                        })
//                        mBinding.rvPokemonList.adapter = mAdapter
//                    }
                    is FavouritesUIState.ShowError -> {
                        Utils.showToastMessage(context = this@FavouritesActivity, uiState.message)
                    }
                    else -> {
                        //Do nothing
                    }
                }
            }
        }
    }

    private fun showPokemonDetailsFragment(pokemonResult: PokemonResult, singlePokemonResponse: SinglePokemonResponse) {
        var frag: PokemonDetailFragment? = null
        frag = PokemonDetailFragment.newInstance(
            pokemonResult = pokemonResult,
            singlePokemonResponse = singlePokemonResponse,
            listener = object : PokemonDetailFragment.OnClickListener {
                override fun onDoneClick() {
                    frag?.let {
                        if (it.isAdded && it.isVisible) {
                            it.dismissAllowingStateLoss()
                        }
                    }
                }

                override fun onAddToFavouriteClick() {
                    frag?.let {
                        if (it.isAdded && it.isVisible) {
                            it.dismissAllowingStateLoss()
                        }
                    }

                    // TODO handle AddToFavourite
                    Utils.showToastMessage(context = this@FavouritesActivity, "Add To Favourite clicked")
                }
            }
        )
        this.showDialogFragment(dialogFragment = frag)
    }
}