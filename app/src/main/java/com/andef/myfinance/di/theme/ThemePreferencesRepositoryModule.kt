package com.andef.myfinance.di.theme

import com.andef.myfinance.data.theme.repository.ThemePreferencesRepositoryImpl
import com.andef.myfinance.di.ApplicationScope
import com.andef.myfinance.domain.theme.repository.ThemePreferencesRepository
import dagger.Binds
import dagger.Module

@Module
interface ThemePreferencesRepositoryModule {
    @Binds
    @ApplicationScope
    fun provideThemePreferencesRepository(
        impl: ThemePreferencesRepositoryImpl
    ): ThemePreferencesRepository
}