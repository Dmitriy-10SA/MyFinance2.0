package com.andef.myfinance.di.viewmodel

import androidx.lifecycle.ViewModel
import com.andef.myfinance.presentation.main.MainActivityViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
interface ViewModelModule {
    @Binds
    @IntoMap
    @ViewModelKey(MainActivityViewModel::class)
    fun bindMainViewModel(impl: MainActivityViewModel): ViewModel
}