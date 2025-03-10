package com.andef.myfinance.presentation.ui.main

import com.andef.myfinance.R

sealed class TopNavigationItem(val index: Int, val nameResId: Int) {
    data object Today : TopNavigationItem(index = 0, nameResId = R.string.day)
    data object Week : TopNavigationItem(index = 1, nameResId = R.string.week)
    data object Month : TopNavigationItem(index = 2, nameResId = R.string.month)
    data object Year : TopNavigationItem(index = 3, nameResId = R.string.year)
    data object Period : TopNavigationItem(index = 4, nameResId = R.string.period)
}