package com.andef.myfinance.presentation.ui.expense

sealed class DetailExpensePieChartState {
    data object Initial : DetailExpensePieChartState()
    data object Loading : DetailExpensePieChartState()
    data class ExpensesAmount(val expensesAmountPercent: List<Float>) : DetailExpensePieChartState()
}