package com.andef.myfinance.di.viewmodel

import androidx.lifecycle.ViewModel
import com.andef.myfinance.presentation.MainViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
interface ViewModelModule {
    @Binds
    @IntoMap
    @ViewModelKey(MainViewModel::class)
    fun bindMainViewModel(impl: MainViewModel): ViewModel
}