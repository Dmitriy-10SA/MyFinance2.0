package com.andef.myfinance.data.theme.repository

import android.content.SharedPreferences
import com.andef.myfinance.domain.theme.repository.ThemePreferencesRepository
import javax.inject.Inject

class ThemePreferencesRepositoryImpl @Inject constructor(
    private val sharedPreferences: SharedPreferences
) : ThemePreferencesRepository {
    override fun isDarkTheme(): Boolean {
        return sharedPreferences.getBoolean(PREF_IS_DARK_THEME, false)
    }

    override fun changeTheme() {
        val currentTheme = sharedPreferences.getBoolean(PREF_IS_DARK_THEME, false)
        sharedPreferences.edit().putBoolean(PREF_IS_DARK_THEME, !currentTheme).apply()
    }

    companion object {
        private const val PREF_IS_DARK_THEME = "is_dark"
    }
}