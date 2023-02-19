package com.izzatismail.pokepal.views.main.view

import android.os.Bundle
import android.view.View
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.izzatismail.pokepal.R
import com.izzatismail.pokepal.base.BaseActivity
import com.izzatismail.pokepal.databinding.ActivityMainBinding
import com.izzatismail.pokepal.utils.Utils
import com.izzatismail.pokepal.views.main.uistate.MainUIState
import com.izzatismail.pokepal.views.main.viewmodel.MainViewModel
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.getViewModel

class MainActivity : BaseActivity() {
    private lateinit var mBinding: ActivityMainBinding
    private lateinit var mViewModel: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        mBinding.executePendingBindings()
        initViewModel()
        mViewModel.getPokemons(limit = null, offset = null)
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
                        Utils.showToastMessage(context = this@MainActivity, "Success!") //Testing. To be removed later
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
}