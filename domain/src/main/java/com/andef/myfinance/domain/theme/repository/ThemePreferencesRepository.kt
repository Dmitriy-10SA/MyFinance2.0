package com.andef.myfinance.domain.theme.repository

interface ThemePreferencesRepository {
    fun isDarkTheme(): Boolean
    fun changeTheme()
}