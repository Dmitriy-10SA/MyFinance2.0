package com.andef.myfinance.presentation.ui.currency

import com.andef.myfinance.R

sealed class CurrencyTopNavigationItem(val index: Int, val nameResId: Int) {
    data object Today : CurrencyTopNavigationItem(index = 0, nameResId = R.string.on_today)
    data object Week : CurrencyTopNavigationItem(index = 1, nameResId = R.string.on_week)
    data object Month : CurrencyTopNavigationItem(index = 2, nameResId = R.string.on_month)
    data object Year : CurrencyTopNavigationItem(index = 3, nameResId = R.string.on_year)
}