package com.andef.myfinance.domain.theme.usecases

import com.andef.myfinance.domain.theme.repository.ThemePreferencesRepository
import javax.inject.Inject

class ChangeThemeUseCase @Inject constructor(
    private val repository: ThemePreferencesRepository
) {
    fun execute() {
        repository.changeTheme()
    }
}