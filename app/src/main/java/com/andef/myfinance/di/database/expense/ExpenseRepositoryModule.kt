package com.andef.myfinance.di.database.expense

import com.andef.myfinance.data.database.expense.repository.ExpenseRepositoryImpl
import com.andef.myfinance.di.ApplicationScope
import com.andef.myfinance.domain.database.expense.repository.ExpenseRepository
import dagger.Binds
import dagger.Module

@Module
interface ExpenseRepositoryModule {
    @ApplicationScope
    @Binds
    fun bindExpenseRepository(impl: ExpenseRepositoryImpl): ExpenseRepository
}