package com.andef.myfinance.presentation.analysis.expense

sealed class ExpenseAnalysisPieChartState {
    data object Initial : ExpenseAnalysisPieChartState()
    data object Loading : ExpenseAnalysisPieChartState()
    data object Error : ExpenseAnalysisPieChartState()
    data class ExpenseAmountPercent(
        val expenseAmountPercent: List<Float>
    ) : ExpenseAnalysisPieChartState()
}