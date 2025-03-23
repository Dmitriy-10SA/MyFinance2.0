package com.andef.myfinance.presentation.currency

import com.andef.myfinance.R

sealed class CurrencyTopBarItem(val index: Int, val titleResId: Int) {
    data object Day : CurrencyTopBarItem(index = 0, titleResId = R.string.on_today)
    data object Week : CurrencyTopBarItem(index = 1, titleResId = R.string.on_week)
    data object Month : CurrencyTopBarItem(index = 2, titleResId = R.string.on_month)
    data object HalfYear : CurrencyTopBarItem(index = 3, titleResId = R.string.on_half_year)
    data object Year : CurrencyTopBarItem(index = 4, titleResId = R.string.on_year)
}