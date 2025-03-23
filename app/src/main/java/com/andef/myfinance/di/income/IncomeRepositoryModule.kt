package com.andef.myfinance.di.income

import com.andef.myfinance.data.income.repository.IncomeRepositoryImpl
import com.andef.myfinance.di.ApplicationScope
import com.andef.myfinance.domain.income.repository.IncomeRepository
import dagger.Binds
import dagger.Module

@Module
interface IncomeRepositoryModule {
    @Binds
    @ApplicationScope
    fun bindIncomeRepository(impl: IncomeRepositoryImpl): IncomeRepository
}