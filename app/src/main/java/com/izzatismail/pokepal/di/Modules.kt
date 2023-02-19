package com.izzatismail.pokepal.di

import com.izzatismail.pokepal.network.ApiHelper
import com.izzatismail.pokepal.network.MainRepository
import com.izzatismail.pokepal.network.retrofit.RetrofitService
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val repositoryModule = module {
    factory { RetrofitService.provideRetrofit() }
    factory { RetrofitService.providePokePalAPI(get()) }

    single { ApiHelper(get()) }
    single { MainRepository(get()) }
}

val viewModelModule = module {
    //Main Pokemon VM
    //viewModel {  }

    //Pokemon Details VM
    //viewModel {  }
}

val allModules = listOf(
    repositoryModule,
    viewModelModule
)