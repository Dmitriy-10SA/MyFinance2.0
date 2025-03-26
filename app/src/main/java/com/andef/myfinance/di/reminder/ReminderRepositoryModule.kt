package com.andef.myfinance.di.reminder

import com.andef.myfinance.data.reminder.repository.ReminderRepositoryImpl
import com.andef.myfinance.di.ApplicationScope
import com.andef.myfinance.domain.reminder.repository.ReminderRepository
import dagger.Binds
import dagger.Module

@Module
interface ReminderRepositoryModule {
    @Binds
    @ApplicationScope
    fun bindReminderRepository(impl: ReminderRepositoryImpl): ReminderRepository
}