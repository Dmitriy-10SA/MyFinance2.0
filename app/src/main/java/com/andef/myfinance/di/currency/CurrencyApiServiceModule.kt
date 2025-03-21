package com.andef.myfinance.di.currency

import com.andef.myfinance.data.currency.api.CurrencyApiFactory
import com.andef.myfinance.data.currency.api.CurrencyApiService
import com.andef.myfinance.di.ApplicationScope
import dagger.Module
import dagger.Provides

@Module
class CurrencyApiServiceModule {
    @Provides
    @ApplicationScope
    fun provideCurrencyApiService(): CurrencyApiService {
        return CurrencyApiFactory.getInstance()
    }
}