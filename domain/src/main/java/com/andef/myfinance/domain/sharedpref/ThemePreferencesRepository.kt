package com.andef.myfinance.domain.sharedpref

interface ThemePreferencesRepository {
    fun isDarkTheme(): Boolean
    fun changeTheme()
}