package com.andef.myfinance.old_presentation.detail

sealed class DetailScreenState {
    data object DatePickerScreen: DetailScreenState()
    data object DetailIncomeOrExpenseScreen: DetailScreenState()
}