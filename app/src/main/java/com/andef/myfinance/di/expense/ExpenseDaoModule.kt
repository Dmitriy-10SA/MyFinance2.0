package com.andef.myfinance.di.expense

import android.app.Application
import com.andef.myfinance.data.expense.datasource.ExpenseDao
import com.andef.myfinance.data.expense.datasource.ExpenseDatabase
import com.andef.myfinance.di.ApplicationScope
import dagger.Module
import dagger.Provides

@Module
class ExpenseDaoModule {
    @Provides
    @ApplicationScope
    fun provideExpenseDao(application: Application): ExpenseDao {
        return ExpenseDatabase.getInstance(application).dao
    }
}