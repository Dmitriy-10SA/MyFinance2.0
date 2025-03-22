package com.andef.myfinance.presentation.main

import com.andef.myfinance.R
import com.andef.myfinance.navigation.Screen

sealed class MainBottomBarItem(
    val titleResId: Int,
    val iconResId: Int,
    val route: String,
    val contentDescriptionResId: Int
) {
    data object Incomes : MainBottomBarItem(
        titleResId = R.string.incomes,
        iconResId = R.drawable.incomes,
        route = Screen.MainScreen.IncomesScreen.route,
        contentDescriptionResId = R.string.incomes
    )

    data object Expenses : MainBottomBarItem(
        titleResId = R.string.expenses,
        iconResId = R.drawable.expenses,
        route = Screen.MainScreen.ExpensesScreen.route,
        contentDescriptionResId = R.string.expenses
    )

    data object Totals : MainBottomBarItem(
        titleResId = R.string.totals,
        iconResId = R.drawable.totals,
        route = Screen.MainScreen.TotalsScreen.route,
        contentDescriptionResId = R.string.totals
    )
}