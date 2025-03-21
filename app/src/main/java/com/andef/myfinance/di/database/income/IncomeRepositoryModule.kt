package com.andef.myfinance.di.database.income

import com.andef.myfinance.data.income.repository.IncomeRepositoryImpl
import com.andef.myfinance.di.ApplicationScope
import com.andef.myfinance.domain.income.repository.IncomeRepository
import dagger.Binds
import dagger.Module

@Module
interface IncomeRepositoryModule {
    @ApplicationScope
    @Binds
    fun bindIncomeRepository(impl: com.andef.myfinance.data.income.repository.IncomeRepositoryImpl): com.andef.myfinance.domain.income.repository.IncomeRepository
}