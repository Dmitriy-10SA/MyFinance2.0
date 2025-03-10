package com.andef.myfinance.di.database.expense

import android.app.Application
import com.andef.myfinance.data.database.expense.datasource.ExpenseDao
import com.andef.myfinance.data.database.expense.datasource.ExpenseDatabase
import com.andef.myfinance.di.ApplicationScope
import dagger.Module
import dagger.Provides

@Module
class ExpenseDaoModule {
    @ApplicationScope
    @Provides
    fun provideExpenseDao(application: Application): ExpenseDao {
        return ExpenseDatabase.getInstance(application).dao
    }
}