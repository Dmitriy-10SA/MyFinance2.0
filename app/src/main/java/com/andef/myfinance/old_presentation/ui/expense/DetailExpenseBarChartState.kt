package com.andef.myfinance.old_presentation.ui.expense

sealed class DetailExpenseBarChartState {
    data object Initial : DetailExpenseBarChartState()
    data object Loading : DetailExpenseBarChartState()
    data object Error : DetailExpenseBarChartState()
    data class ExpensesAmount(val expensesAmount: List<Double>) : DetailExpenseBarChartState()
}