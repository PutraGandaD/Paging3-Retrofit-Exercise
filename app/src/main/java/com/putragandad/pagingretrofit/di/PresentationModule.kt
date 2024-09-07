package com.putragandad.pagingretrofit.di

import com.putragandad.pagingretrofit.presentation.MainViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

object PresentationModule {
    val viewModelModule = module {
        viewModel { MainViewModel(get()) }
    }
}