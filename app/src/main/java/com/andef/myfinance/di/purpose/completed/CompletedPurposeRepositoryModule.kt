package com.andef.myfinance.di.purpose.completed

import com.andef.myfinance.data.purpose.completed.repository.CompletedPurposeRepositoryImpl
import com.andef.myfinance.di.ApplicationScope
import com.andef.myfinance.domain.purpose.completed.repository.CompletedPurposeRepository
import dagger.Binds
import dagger.Module

@Module
interface CompletedPurposeRepositoryModule {
    @Binds
    @ApplicationScope
    fun bindCompletedPurposeRepository(impl: CompletedPurposeRepositoryImpl): CompletedPurposeRepository
}