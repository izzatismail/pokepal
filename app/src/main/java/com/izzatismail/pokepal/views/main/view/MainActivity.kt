package com.izzatismail.pokepal.views.main.view

import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.lifecycleScope
import com.izzatismail.pokepal.R
import com.izzatismail.pokepal.base.BaseActivity
import com.izzatismail.pokepal.databinding.ActivityMainBinding
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
    }

    private fun initViewModel() {
        mViewModel = getViewModel()
        lifecycleScope.launch {

        }
    }
}