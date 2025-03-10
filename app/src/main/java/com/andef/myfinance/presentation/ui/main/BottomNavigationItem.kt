package com.andef.myfinance.presentation.ui.main

import com.andef.myfinance.R
import com.andef.myfinance.navigation.main.MainScreenRoutes

sealed class BottomNavigationItem(
    val route: String,
    val imageResId: Int,
    val nameResId: Int
) {
    data object Incomes : BottomNavigationItem(
        route = MainScreenRoutes.IncomesScreen.route,
        imageResId = R.drawable.incomes,
        nameResId = R.string.incomes
    )

    data object Expenses : BottomNavigationItem(
        route = MainScreenRoutes.ExpensesScreen.route,
        imageResId = R.drawable.expenses,
        nameResId = R.string.expenses
    )

    data object Totals : BottomNavigationItem(
        route = MainScreenRoutes.TotalsScreen.route,
        imageResId = R.drawable.totals,
        nameResId = R.string.totals
    )
}