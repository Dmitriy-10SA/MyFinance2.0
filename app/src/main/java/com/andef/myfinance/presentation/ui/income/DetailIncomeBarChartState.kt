package com.andef.myfinance.presentation.ui.income

sealed class DetailIncomeBarChartState {
    data object Initial : DetailIncomeBarChartState()
    data object Loading : DetailIncomeBarChartState()
    data object Error : DetailIncomeBarChartState()
    data class IncomesAmount(val incomesAmount: List<Double>) : DetailIncomeBarChartState()
}