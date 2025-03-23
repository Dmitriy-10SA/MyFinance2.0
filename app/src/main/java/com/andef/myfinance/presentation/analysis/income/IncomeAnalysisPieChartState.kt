package com.andef.myfinance.presentation.analysis.income

sealed class IncomeAnalysisPieChartState {
    data object Initial : IncomeAnalysisPieChartState()
    data object Loading : IncomeAnalysisPieChartState()
    data object Error : IncomeAnalysisPieChartState()
    data class IncomesAmountPercent(
        val incomesAmountPercent: List<Float>
    ) : IncomeAnalysisPieChartState()
}