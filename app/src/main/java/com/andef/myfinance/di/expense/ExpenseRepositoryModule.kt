package com.andef.myfinance.di.expense

import com.andef.myfinance.data.expense.repository.ExpenseRepositoryImpl
import com.andef.myfinance.di.ApplicationScope
import com.andef.myfinance.domain.expense.repository.ExpenseRepository
import dagger.Binds
import dagger.Module

@Module
interface ExpenseRepositoryModule {
    @Binds
    @ApplicationScope
    fun bindExpenseRepository(impl: ExpenseRepositoryImpl): ExpenseRepository
}