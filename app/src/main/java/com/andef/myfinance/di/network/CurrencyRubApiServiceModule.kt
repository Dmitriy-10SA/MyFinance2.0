package com.andef.myfinance.di.network

import com.andef.myfinance.data.currency.api.CurrencyRubApiFactory
import com.andef.myfinance.data.currency.api.CurrencyRubApiService
import com.andef.myfinance.di.ApplicationScope
import dagger.Module
import dagger.Provides

@Module
class CurrencyRubApiServiceModule {
    @ApplicationScope
    @Provides
    fun provideCurrencyRunApiService(): com.andef.myfinance.data.currency.api.CurrencyRubApiService = com.andef.myfinance.data.currency.api.CurrencyRubApiFactory.getInstance()
}