package com.andef.myfinance.presentation.main

import com.andef.myfinance.R

sealed class MainTopBarItem(val index: Int, val titleResId: Int) {
    data object Day : MainTopBarItem(index = 0, titleResId = R.string.day)
    data object Week : MainTopBarItem(index = 1, titleResId = R.string.week)
    data object Month : MainTopBarItem(index = 2, titleResId = R.string.month)
    data object Year : MainTopBarItem(index = 3, titleResId = R.string.year)
    data object Period : MainTopBarItem(index = 4, titleResId = R.string.period)
}