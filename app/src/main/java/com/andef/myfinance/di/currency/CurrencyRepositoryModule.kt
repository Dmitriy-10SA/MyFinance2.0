package com.andef.myfinance.di.currency

import com.andef.myfinance.data.currency.repository.CurrencyRepositoryImpl
import com.andef.myfinance.di.ApplicationScope
import com.andef.myfinance.domain.currency.repository.CurrencyRepository
import dagger.Binds
import dagger.Module

@Module
interface CurrencyRepositoryModule {
    @Binds
    @ApplicationScope
    fun bindCurrencyRepository(impl: CurrencyRepositoryImpl): CurrencyRepository
}