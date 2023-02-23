package com.izzatismail.pokepal.views.main.view

import android.os.Bundle
import android.view.View
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.lifecycleScope
import com.izzatismail.pokepal.R
import com.izzatismail.pokepal.base.BaseActivity
import com.izzatismail.pokepal.databinding.ActivityMainBinding
import com.izzatismail.pokepal.model.PokemonResult
import com.izzatismail.pokepal.model.response.SinglePokemonResponse
import com.izzatismail.pokepal.utils.Utils
import com.izzatismail.pokepal.utils.showDialogFragment
import com.izzatismail.pokepal.views.favourites.view.FavouritesActivity
import com.izzatismail.pokepal.views.main.adapters.PokemonListAdapter
import com.izzatismail.pokepal.views.main.adapters.PokemonListListener
import com.izzatismail.pokepal.views.main.uistate.MainUIState
import com.izzatismail.pokepal.views.main.viewmodel.MainViewModel
import org.koin.androidx.viewmodel.ext.android.getViewModel

class MainActivity : BaseActivity() {
    private lateinit var mBinding: ActivityMainBinding
    private lateinit var mViewModel: MainViewModel

    private var mAdapter: PokemonListAdapter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        mBinding.executePendingBindings()
        hideActionBar()
        initViewModel()
        initListeners()
        mViewModel.getPokemons(limit = null, offset = null) //TODO handle limit & offset dynamically based on pagination
    }

    private fun initViewModel() {
        mViewModel = getViewModel()
        lifecycleScope.launchWhenStarted {
            mViewModel.uiState.collect { uiState ->
                when (uiState) {
                    is MainUIState.IsLoading -> {
                        mBinding.pbProgressBar.visibility = if (uiState.isLoading) View.VISIBLE else View.GONE
                    }
                    is MainUIState.SuccessResponse -> {
                        mBinding.swipeRefreshRoot.isRefreshing = false
                        mAdapter = PokemonListAdapter(pokemonList = uiState.response.results, listener =  object: PokemonListListener {
                            override fun onClick(pokemonResult: PokemonResult) {
                                mViewModel.getSinglePokemonData(pokemonResult = pokemonResult)
                            }
                        })
                        mBinding.rvPokemonList.adapter = mAdapter
                    }
                    is MainUIState.SinglePokemonSuccessResponse -> {
                        showPokemonDetailsFragment(pokemonResult = uiState.selectedPokemon, singlePokemonResponse = uiState.response)
                    }
                    is MainUIState.ShowError -> {
                        Utils.showToastMessage(context = this@MainActivity, uiState.message)
                    }
                    else -> {
                        //Do nothing
                    }
                }
            }
        }
    }

    private fun initListeners() {
        mBinding.swipeRefreshRoot.setOnRefreshListener {
            mViewModel.getPokemons(limit = null, offset = null) //TODO handle limit & offset dynamically based on pagination
        }

        mBinding.fabFavourite.setOnClickListener {
            FavouritesActivity.startActivity(context = this@MainActivity)
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

                override fun onAddToFavouriteClick(pokemonResult: PokemonResult, singlePokemonResponse: SinglePokemonResponse) {
                    frag?.let {
                        if (it.isAdded && it.isVisible) {
                            it.dismissAllowingStateLoss()
                        }
                    }

                    mViewModel.addPokemonToFavourite(pokemonResult = pokemonResult, singlePokemonResponse = singlePokemonResponse)
                    Utils.showToastMessage(context = this@MainActivity, "Add To Favourite clicked")
                }
            }
        )
        this.showDialogFragment(dialogFragment = frag)
    }
}