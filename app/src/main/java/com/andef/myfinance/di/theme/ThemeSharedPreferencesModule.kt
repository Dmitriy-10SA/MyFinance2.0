package com.andef.myfinance.di.theme

import android.app.Application
import android.content.Context.MODE_PRIVATE
import android.content.SharedPreferences
import com.andef.myfinance.di.ApplicationScope
import dagger.Module
import dagger.Provides

@Module
class ThemeSharedPreferencesModule {
    @Provides
    @ApplicationScope
    fun provideThemeSharedPreferences(application: Application): SharedPreferences {
        return application.getSharedPreferences(PREFS_THEME_FILE, MODE_PRIVATE)
    }

    companion object {
        private const val PREFS_THEME_FILE = "theme"
    }
}