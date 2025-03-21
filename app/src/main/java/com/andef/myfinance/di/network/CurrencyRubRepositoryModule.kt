package com.andef.myfinance.di.network

import com.andef.myfinance.data.currency.repository.CurrencyRubRepositoryImpl
import com.andef.myfinance.di.ApplicationScope
import com.andef.myfinance.domain.currency.repository.CurrencyRubRepository
import dagger.Binds
import dagger.Module

@Module
interface CurrencyRubRepositoryModule {
    @ApplicationScope
    @Binds
    fun bindCurrencyRubRepository(impl: com.andef.myfinance.data.currency.repository.CurrencyRubRepositoryImpl): com.andef.myfinance.domain.currency.repository.CurrencyRubRepository
}