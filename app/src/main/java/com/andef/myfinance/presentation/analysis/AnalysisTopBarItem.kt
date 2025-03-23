package com.andef.myfinance.presentation.analysis

import com.andef.myfinance.R

sealed class AnalysisTopBarItem(val index: Int, val titleResId: Int) {
    data object Day : AnalysisTopBarItem(index = 0, titleResId = R.string.day)
    data object Week : AnalysisTopBarItem(index = 1, titleResId = R.string.week)
    data object Month : AnalysisTopBarItem(index = 2, titleResId = R.string.month)
    data object Year : AnalysisTopBarItem(index = 3, titleResId = R.string.year)
    data object Period : AnalysisTopBarItem(index = 4, titleResId = R.string.period)
}