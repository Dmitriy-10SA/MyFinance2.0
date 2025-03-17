package com.andef.myfinance.presentation.ui.income

sealed class DetailIncomePieChartState  {
    data object Initial : DetailIncomePieChartState()
    data object Loading : DetailIncomePieChartState()
    data object Error : DetailIncomePieChartState()
    data class IncomesAmount(val incomesAmountPercent: List<Float>) : DetailIncomePieChartState()
}