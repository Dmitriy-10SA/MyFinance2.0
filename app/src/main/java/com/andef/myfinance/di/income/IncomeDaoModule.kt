package com.andef.myfinance.di.income

import android.app.Application
import com.andef.myfinance.data.income.datasource.IncomeDao
import com.andef.myfinance.data.income.datasource.IncomeDatabase
import com.andef.myfinance.di.ApplicationScope
import dagger.Module
import dagger.Provides

@Module
class IncomeDaoModule {
    @Provides
    @ApplicationScope
    fun provideIncomeDao(application: Application): IncomeDao {
        return IncomeDatabase.getInstance(application).dao
    }
}