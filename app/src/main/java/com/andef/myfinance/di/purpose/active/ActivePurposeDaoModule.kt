package com.andef.myfinance.di.purpose.active

import android.app.Application
import com.andef.myfinance.data.purpose.active.datasource.ActivePurposeDao
import com.andef.myfinance.data.purpose.active.datasource.ActivePurposeDatabase
import com.andef.myfinance.di.ApplicationScope
import dagger.Module
import dagger.Provides

@Module
class ActivePurposeDaoModule {
    @Provides
    @ApplicationScope
    fun provideActivePurposeDao(application: Application): ActivePurposeDao {
        return ActivePurposeDatabase.getInstance(application).dao
    }
}