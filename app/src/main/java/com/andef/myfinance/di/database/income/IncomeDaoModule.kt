package com.andef.myfinance.di.database.income

import android.app.Application
import com.andef.myfinance.data.income.datasource.IncomeDao
import com.andef.myfinance.data.income.datasource.IncomeDatabase
import com.andef.myfinance.di.ApplicationScope
import dagger.Module
import dagger.Provides

@Module
class IncomeDaoModule {
    @ApplicationScope
    @Provides
    fun incomeDaoProvide(application: Application): com.andef.myfinance.data.income.datasource.IncomeDao {
        return com.andef.myfinance.data.income.datasource.IncomeDatabase.getInstance(application).dao
    }
}