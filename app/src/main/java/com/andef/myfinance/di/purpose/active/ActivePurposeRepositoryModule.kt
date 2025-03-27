package com.andef.myfinance.di.purpose.active

import com.andef.myfinance.data.purpose.active.repository.ActivePurposeRepositoryImpl
import com.andef.myfinance.di.ApplicationScope
import com.andef.myfinance.domain.purpose.active.repository.ActivePurposeRepository
import dagger.Binds
import dagger.Module

@Module
interface ActivePurposeRepositoryModule {
    @Binds
    @ApplicationScope
    fun bindActivePurposeRepository(impl: ActivePurposeRepositoryImpl): ActivePurposeRepository
}