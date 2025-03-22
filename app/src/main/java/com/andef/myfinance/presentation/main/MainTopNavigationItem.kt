package com.andef.myfinance.presentation.main

import com.andef.myfinance.R

sealed class MainTopNavigationItem(val index: Int, val nameResId: Int) {
    data object Today : MainTopNavigationItem(index = 0, nameResId = R.string.day)
    data object Week : MainTopNavigationItem(index = 1, nameResId = R.string.week)
    data object Month : MainTopNavigationItem(index = 2, nameResId = R.string.month)
    data object Year : MainTopNavigationItem(index = 3, nameResId = R.string.year)
    data object Period : MainTopNavigationItem(index = 4, nameResId = R.string.period)
}