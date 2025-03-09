package com.andef.myfinance.presentation.screens.main

import com.andef.myfinance.R

sealed class NavigationItem(
    val imageResId: Int,
    val nameResId: Int
) {
    data object Incomes : NavigationItem(
        imageResId = R.drawable.incomes,
        nameResId = R.string.incomes
    )

    data object Expenses : NavigationItem(
        imageResId = R.drawable.expenses,
        nameResId = R.string.expenses
    )

    data object Totals : NavigationItem(
        imageResId = R.drawable.totals,
        nameResId = R.string.totals
    )
}