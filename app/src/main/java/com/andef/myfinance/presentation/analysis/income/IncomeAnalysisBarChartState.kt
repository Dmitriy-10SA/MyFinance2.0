package com.andef.myfinance.presentation.analysis.income

sealed class IncomeAnalysisBarChartState {
    data object Initial : IncomeAnalysisBarChartState()
    data object Loading : IncomeAnalysisBarChartState()
    data object Error : IncomeAnalysisBarChartState()
    data class IncomesAmount(val incomesAmount: List<Double>) : IncomeAnalysisBarChartState()
}