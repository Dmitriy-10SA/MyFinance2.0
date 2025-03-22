package com.andef.myfinance.presentation.main

import androidx.lifecycle.ViewModel
import com.andef.myfinance.domain.theme.usecases.ChangeThemeUseCase
import com.andef.myfinance.domain.theme.usecases.IsDarkThemeUseCase
import javax.inject.Inject

class MainActivityViewModel @Inject constructor(
    private val isDarkThemeUseCase: IsDarkThemeUseCase,
    private val changeThemeUseCase: ChangeThemeUseCase
) : ViewModel() {
    fun isDarkTheme(): Boolean {
        return isDarkThemeUseCase.execute()
    }

    fun changeThemeUseCase() {
        changeThemeUseCase.execute()
    }
}