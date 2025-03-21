package com.andef.myfinance.di.database.expense

import android.app.Application
import com.andef.myfinance.data.expense.datasource.ExpenseDao
import com.andef.myfinance.data.expense.datasource.ExpenseDatabase
import com.andef.myfinance.di.ApplicationScope
import dagger.Module
import dagger.Provides

@Module
class ExpenseDaoModule {
    @ApplicationScope
    @Provides
    fun provideExpenseDao(application: Application): com.andef.myfinance.data.expense.datasource.ExpenseDao {
        return com.andef.myfinance.data.expense.datasource.ExpenseDatabase.getInstance(application).dao
    }
}