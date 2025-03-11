package com.andef.myfinance.presentation.ui.main

sealed class MainScreenState {
    data object DatePickerScreen : MainScreenState()
    data object AnyScreenWithTopAndBottomNav : MainScreenState()
    data object IncomeScreenForAdd: MainScreenState()
}