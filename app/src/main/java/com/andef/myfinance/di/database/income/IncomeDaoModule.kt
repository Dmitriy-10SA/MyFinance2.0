package com.andef.myfinance.di.database.income

import android.app.Application
import com.andef.myfinance.data.database.income.datasource.IncomeDao
import com.andef.myfinance.data.database.income.datasource.IncomeDatabase
import com.andef.myfinance.di.ApplicationScope
import dagger.Module
import dagger.Provides

@Module
class IncomeDaoModule {
    @ApplicationScope
    @Provides
    fun incomeDaoProvide(application: Application): IncomeDao {
        return IncomeDatabase.getInstance(application).dao
    }
}