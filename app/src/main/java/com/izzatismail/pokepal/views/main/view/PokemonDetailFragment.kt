package com.izzatismail.pokepal.views.main.view

import android.app.Dialog
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.*
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.DialogFragment
import com.izzatismail.pokepal.R
import com.izzatismail.pokepal.databinding.FragmentPokemonDetailsBinding
import com.izzatismail.pokepal.model.PokemonResult
import com.izzatismail.pokepal.model.response.SinglePokemonResponse
import com.izzatismail.pokepal.views.main.adapters.PokemonStatsListAdapter

class PokemonDetailFragment: DialogFragment() {

    private lateinit var mBinding: FragmentPokemonDetailsBinding
    private var mPokemonResult: PokemonResult = PokemonResult(name = "", url = "")
    private var mListener: OnClickListener? = null
    private var mSinglePokemonResult: SinglePokemonResponse? = null
    private var mAdapter: PokemonStatsListAdapter? = null

    companion object {
        fun newInstance(pokemonResult: PokemonResult, singlePokemonResponse: SinglePokemonResponse, listener: OnClickListener): PokemonDetailFragment {
            val frag = PokemonDetailFragment()
            frag.mPokemonResult = pokemonResult
            frag.mListener = listener
            frag.mSinglePokemonResult = singlePokemonResponse
            return frag
        }
    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val dialog =  super.onCreateDialog(savedInstanceState)
        dialog.window?.requestFeature(Window.FEATURE_NO_TITLE)
        dialog.setCanceledOnTouchOutside(true)
        return dialog
    }

    override fun onStart() {
        super.onStart()
        dialog?.window?.setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        dialog?.window?.setBackgroundDrawable(ColorDrawable(android.graphics.Color.TRANSPARENT))
        mBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_pokemon_details, container, false)
        return mBinding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        dialog?.window?.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN)

        mBinding.pokemon = mPokemonResult
        mBinding.data = mSinglePokemonResult
        mBinding.btnAddFavourite.setOnClickListener { //Todo for future improvement, move this to XML
            mListener?.onAddToFavouriteClick(pokemonResult = mPokemonResult, singlePokemonResponse = mSinglePokemonResult!!)
        }
        mBinding.btnDone.setOnClickListener { //Todo for future improvement, move this to XML
            mListener?.onDoneClick()
        }

        mSinglePokemonResult?.let { singlePokemonResponse ->
            mAdapter = PokemonStatsListAdapter(statsList = singlePokemonResponse.stats)
            mBinding.rvStatsList.adapter = mAdapter
        }
    }

    interface OnClickListener {
        fun onDoneClick()
        fun onAddToFavouriteClick(pokemonResult: PokemonResult, singlePokemonResponse: SinglePokemonResponse)
    }
}