package com.andef.myfinance.presentation.detail

sealed class DetailScreenState {
    data object DatePickerScreen: DetailScreenState()
    data object DetailIncomeOrExpenseScreen: DetailScreenState()
}