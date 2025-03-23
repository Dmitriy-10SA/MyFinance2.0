package com.andef.myfinance.presentation.analysis.expense

sealed class ExpenseAnalysisBarChartState {
    data object Initial : ExpenseAnalysisBarChartState()
    data object Loading : ExpenseAnalysisBarChartState()
    data object Error : ExpenseAnalysisBarChartState()
    data class ExpenseAmount(val expenseAmount: List<Double>) : ExpenseAnalysisBarChartState()
}