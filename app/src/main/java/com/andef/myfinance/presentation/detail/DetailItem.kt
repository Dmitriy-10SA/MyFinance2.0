package com.andef.myfinance.presentation.detail

import com.andef.myfinance.R

sealed class DetailItem(val nameResId: Int) {
    data object PieChart : DetailItem(nameResId = R.string.pieChart)
    data object BarChart : DetailItem(nameResId = R.string.barChart)
}