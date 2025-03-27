package com.andef.myfinance.di.purpose.completed

import android.app.Application
import com.andef.myfinance.data.purpose.completed.datasource.CompletedPurposeDao
import com.andef.myfinance.data.purpose.completed.datasource.CompletedPurposeDatabase
import com.andef.myfinance.di.ApplicationScope
import dagger.Module
import dagger.Provides

@Module
class CompletedPurposeDaoModule {
    @Provides
    @ApplicationScope
    fun provideCompletedPurposeDao(application: Application): CompletedPurposeDao {
        return CompletedPurposeDatabase.getInstance(application).dao
    }
}