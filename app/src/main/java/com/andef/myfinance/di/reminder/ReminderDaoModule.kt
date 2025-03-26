package com.andef.myfinance.di.reminder

import android.app.Application
import com.andef.myfinance.data.reminder.datasource.ReminderDao
import com.andef.myfinance.data.reminder.datasource.ReminderDatabase
import com.andef.myfinance.di.ApplicationScope
import dagger.Module
import dagger.Provides

@Module
class ReminderDaoModule {
    @Provides
    @ApplicationScope
    fun provideReminderDao(application: Application): ReminderDao {
        return ReminderDatabase.getInstance(application).dao
    }
}