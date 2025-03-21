package com.andef.myfinance.data.sharedpref

import android.content.SharedPreferences
import com.andef.myfinance.domain.sharedpref.ThemePreferencesRepository
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
        private const val PREFS_THEME_FILE = "theme"
        private const val PREF_IS_DARK_THEME = "is_dark"
    }
}