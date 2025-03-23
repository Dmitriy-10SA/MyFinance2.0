package com.andef.myfinance.domain.theme.usecases

import com.andef.myfinance.domain.theme.repository.ThemePreferencesRepository
import javax.inject.Inject

class IsDarkThemeUseCase @Inject constructor(
    private val repository: ThemePreferencesRepository
) {
    fun execute(): Boolean {
        return repository.isDarkTheme()
    }
}