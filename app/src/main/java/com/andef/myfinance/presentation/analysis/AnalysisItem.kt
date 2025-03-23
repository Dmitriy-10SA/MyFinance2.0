package com.andef.myfinance.presentation.analysis

import com.andef.myfinance.R

sealed class AnalysisItem(val nameResId: Int) {
    data object PieChart : AnalysisItem(nameResId = R.string.pieChart)
    data object BarChart : AnalysisItem(nameResId = R.string.barChart)
}