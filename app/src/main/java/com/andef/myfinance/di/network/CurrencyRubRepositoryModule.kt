package com.andef.myfinance.di.network

import com.andef.myfinance.data.network.currency.repository.CurrencyRubRepositoryImpl
import com.andef.myfinance.di.ApplicationScope
import com.andef.myfinance.domain.network.currency.repository.CurrencyRubRepository
import dagger.Binds
import dagger.Module

@Module
interface CurrencyRubRepositoryModule {
    @ApplicationScope
    @Binds
    fun bindCurrencyRubRepository(impl: CurrencyRubRepositoryImpl): CurrencyRubRepository
}