package com.andef.myfinance.di.network

import com.andef.myfinance.data.network.currency.api.CurrencyRubApiFactory
import com.andef.myfinance.data.network.currency.api.CurrencyRubApiService
import com.andef.myfinance.di.ApplicationScope
import dagger.Module
import dagger.Provides

@Module
class CurrencyRubApiServiceModule {
    @ApplicationScope
    @Provides
    fun provideCurrencyRunApiService(): CurrencyRubApiService = CurrencyRubApiFactory.getInstance()
}