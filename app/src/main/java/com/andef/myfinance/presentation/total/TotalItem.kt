package com.andef.myfinance.presentation.total

import com.andef.myfinance.R

sealed class TotalItem(val nameResId: Int) {
    data object PieChart : TotalItem(nameResId = R.string.pieChart)
    data object BarChart : TotalItem(nameResId = R.string.barChart)
}